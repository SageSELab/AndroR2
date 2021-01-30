import requests
import re
import time
import datetime
import math
import sys
import getopt
import subprocess
import os
import signal
import argparse
import threading
import json
import datetime
import urllib
from pymongo import MongoClient
#Default mongoDB client. Assumes host is localhost and port is 27017.
client = MongoClient()
token = ""
days_to_search = 3000

def determine_if_android(repo):
    """given a repo, performs a query such that we know if the repo is for
    an Android app or not. Uses a hash table to prevent additoinaly querying."""
    isAndroid = check_if_repo_is_android(repo)
    if isAndroid in [True, False]:
        #print("cache hit, repo", repo, " is ", isAndroid)
        return isAndroid
    #If not in the hashtable, perform a github query to see if AndroidManifest file exists.
    query_to_determine_android = "https://api.github.com/search/code?q=repo:" + repo + "+" + "extension:xml+filename:AndroidManifest"
    query_to_determine_android_result = execute_github_query(query_to_determine_android)
    if "query_exception" in query_to_determine_android_result:
        print("Exception while determining whether an androidManifest file exists", flush=True)
        return
    #print(query_to_determine_android_result)
    if 'total_count' not in query_to_determine_android_result:
        #An error occured
        print("Query error: incorrect data returned.")
        print("the query was: ", query_to_determine_android)
        print("the result was: ", query_to_determine_android_result)
        return None
        #Either we hit a rate limit or some other error occured.
    else:
        #A successful search result. Now to filter out those where num_repos < 1
        num_repos = (query_to_determine_android_result['total_count'])
        #print("added repo" , repo, "to database")
        if num_repos > 0:
            insert_repo_into_mongo(repo, True)
            return True
        else:
            insert_repo_into_mongo(repo, False)
            return False
def execute_github_query(query):
    """Given a query, performs a GET request using Github's API
    Includes error checks to warn if the rate limit is reached
    or if a faulty request is sent.

    Largely unchanged from Dr. Fazzini's code. 
    """
    request_wait_time = 2
    while True:
        time.sleep(request_wait_time)
        query_response = requests.get(query, headers={'Accept': 'application/vnd.github.symmetra-preview+json', 'Authorization' : "token " + token})
        try:
            query_results = query_response.json()
            if "documentation_url" in query_results and "rate-limit" in query_results["documentation_url"]:
                print("Rate limit reached", flush=True)
                continue
        except ValueError:
            query_results = {}
            query_results["query_exception"] = "query_exception"
            return query_results
        except KeyboardInterrupt:
            print("keyboard interrupted.")
            exit()
        except:
            print("Network connection error. Trying again...")
            time.sleep(10)
            continue
        return query_results


def search_github_by_day(query_string, date):
    """Modified version of original.
    'query-string' is the string we want to search for. Can be empty.
    'date' is the date the issue was published. In the form 'YYYY-MM-DD'.
    Returns an array of issues that can be formatted easily into JSON.
    The attributes of the JSON can be modified below
    """
    starting_time = time.time()
    #print("starting time="+str(starting_time), flush=True)
    search_str = "\""+query_string+"\""
    #get total number of results that github can give us
    query_based_on_string_and_file = "https://api.github.com/search/issues?q=" + search_str + "language:java+label:bug+created:" + date
    print("Requesting query ", query_based_on_string_and_file)
    
    query_based_on_string_and_file_result = execute_github_query(query_based_on_string_and_file)
    
    #Github query is executed. Need to check for errors/exceptions..
    if "query_exception" in query_based_on_string_and_file_result:
        print("Exception while retrieving repos with string and file name for total count", flush=True)
        return
    
    total_count = query_based_on_string_and_file_result["total_count"]
    print(str(total_count)+" results for search based on search string:"+search_str+" on date:"+date, flush=True)
    
    #nothing to process
    if total_count == 0:
        print("No result found")
        return

    #asking 100 resutls per page
    tot_page_num = math.ceil(total_count / 100)
    #print("Total number of result pages:"+str(tot_page_num))
    
    issues = [] #Array of issues that will be returned
    count = 0
    for page_num in range (tot_page_num):
        curr_page_num = page_num + 1
        # github allows to check only first 1000 results

        if curr_page_num == 11:#curr_page_num == 11:
            print("Number of issues exceeds 1000. Investigate date", date)
            
            break
        #print(curr_page_num)
        query_based_on_string_and_file_page = "https://api.github.com/search/issues?q=" + search_str + "language:java+label:bug+created:" + date + "&page="+str(curr_page_num)+"&per_page=100"
        query_based_on_string_and_file_page_result = execute_github_query(query_based_on_string_and_file_page)
        print(query_based_on_string_and_file_page)
        if "query_exception" in query_based_on_string_and_file_page_result:
            print("Exception while retrieving repos with string and file name per page", flush=True)
            return
        
        if "items" not in query_based_on_string_and_file_page_result:
            print('something went wrong for query, ' ,query_based_on_string_and_file_page_result)
        for item in query_based_on_string_and_file_page_result["items"]:
            _id = item["url"].split("/")[-4] + "-" + item["url"].split("/")[-3] + "-" + item["url"].split("/")[-1]
            if not check_if_in_mongo(_id):
                #Iterates through issues and adds them to our issues list.
                new_issue = createIssue(item, date)
                if new_issue != None:
                    insert_issue_into_mongo(new_issue)
                    issues.append(new_issue)
                    print("added issue", _id, "for date", date)
                    count += 1
            else:
                pass
                #print("Skipping issue", _id, "because it is already in the database.")
    
    #If we have made it far, this means that we have inserted all issues
    #from this date into our db. We can tell the db that this date is
    #done
    insert_date_into_mongo(date)
    ending_time = time.time() - starting_time
    print("added", count, "issues for date", date, ". This took ", ending_time, "seconds to complete.")
    return issues

def createIssue( item , date):
    """ Given a repo, create an object that holds pertinent data.
    Returns None if the repo is not for an Android app."""
    repo_to_search = item["repository_url"].split('/')
    repo_to_search = repo_to_search[-2] + '/' + repo_to_search[-1]
    isAndroid = determine_if_android(repo_to_search)
    if isAndroid:
        #We need to add all github comments (if any) to this array.
        comments = [] #list of all strings of comments for this issue
        if item['comments'] > 0:
            comments_query = item['url'] + '/comments'
            comments_query_result = execute_github_query(comments_query)
            for comment in comments_query_result:
                comments.append({'body':comment['body'], 'created_at': comment['created_at']})
        new_issue = dict()
        for key in ['title', 'url', 'body', 'labels', 'created_at', 'closed_at']: # List of keywords to add to JSON
            new_issue[key] = item[key]
        new_issue['date'] = date
        new_issue['comments'] = comments
        new_issue['retrieved_at'] = str(datetime.datetime.now())
        #Give the issue of a primary key by formatting the url
        #Example: sampleRepo/issues/111 becomes sampleRepo-111
        new_issue["_id"] = new_issue["url"].split("/")[-4] + "-" + new_issue["url"].split("/")[-3] + "-" + new_issue["url"].split("/")[-1]
        #print("Added issue " + new_issue['_id'] )
        return new_issue
    else:
        return None

def get_date_list(days):

    #TODO (Tyler):
    # add date to each issue
    # change _id = 
    # closed : still open
    # Build a list of dates:
    today = datetime.datetime.today()
    total_issues = 0
    issues = []
    #list of dates from now until 'days' days from now.
    date_list = [datetime.datetime.today() - datetime.timedelta(days=x) for x in range(days) ]
    date_list = [str(x.year) + '-' + str(x.month) + '-' + str(x.day) for x in date_list]
    for i in range(len(date_list)):
        if date_list[i][-2] == "-":
            #If the date is single-digits i.e. 2019-10-1 -> 2019-10-01
            date_list[i] = date_list[i][:-1] + "0" + date_list[i][-1]
        if date_list[i][-5] == "-":
            date_list[i] = date_list[i][:-4] + "0" + date_list[i][-4] + date_list[i][-3:]
    return date_list
    #date_list is now formatted to match github queries

def insert_repo_into_mongo(_id, isAndroid):
    db = client.issues
    repos = db.repos
    repos.insert_one( {"_id" : _id , "isAndroid": isAndroid } )
def check_if_repo_is_android(_id):
    """Returns true/false if a repo is known to be Android or not
    Returns none if not known"""
    db = client.issues
    repos = db.repos
    if (len(list(repos.find({"_id": _id}).limit(1)))) == 1:
        for record in repos.find( { "_id" : _id } ):
            return record["isAndroid"]
    else:
        return None

def check_if_in_mongo(_id):
    """Returns 1 if an issue's ID in the mongoDB database, 0 otherwise."""
    db = client.issues
    issues = db.issues
    return (len(list(issues.find({"_id" : _id}).limit(1))))

def insert_issue_into_mongo(issue):
    """inserts an issue JSON object into mongoDB"""
    db = client.issues
    issues = db.issues
    issues.insert_one(issue)

def insert_date_into_mongo(date):
    """inserts a date JSON object into mongoDB"""
    db = client.issues
    issues = db.dates
    if not date_fully_inserted(date):
        issues.insert_one({"_id" : date})


def date_fully_inserted(date):
    """Lets us know if we have already crawled over an entire date"""
    db = client.issues
    dates = db.dates
    return (len(list(dates.find({"_id": date}).limit(1))))
    
def main():
    


    date_list = get_date_list(days_to_search)

    for i, date in enumerate(date_list):
        #Check whether we've already put all issues for that day in the list
        #We can't do this for today, however, bc people might not have finished
        #putting in issues today.
        if not date_fully_inserted(date):
            try:
                search_github_by_day("", date)
                #print("Inserted date ", date, "into database.")
            except ConnectionError:
                print("Network connection error. Trying again...")
                time.sleep(10)
                search_github_by_day("", date)
            except requests.ConnectionError:
                print("Network connection error. Trying again...")
                time.sleep(10)
                search_github_by_day("", date)

        else:
            print("Skipped date" +  date +  ": already in database.")
            
if __name__ == "__main__":
   main()

