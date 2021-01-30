# Issue Crawler using Github Search

## Before starting up the program, make sure the following things are taken care of:
* Pymongo is installed on your machine. This can be downloaded by running `pip install pymongo` in a terminal.
* Your local machine is able to host MongoDB servers. This project was configured using MongoDB Compass, but should be compatible with any version.
* You enter your Github account token on line 19 of the file. Because this heavily utilizes Github's search API, queries must be sent with an attached account or they will be rejected.
* You specify the number of days you want the script to search for on line 20. The crawler searches by days starting on the current date and moving backwards, so '30' would be the most recent month.

## Running the program

In a terminal, run the following command:

`python githubSearch.py`

The script will continue until it has searched all of the days given to it. Issues are printed to standard out as they are added to the database. A notice is given when the system is on hold because of network issues or while waiting for the API rate limit to reset.


Data will appear in the "issues" collection of the database. Within this are the following:

* issues, the issues pulled from Github repositories
* repos, all repos pulled from Github repositories. These are saved locally to avoid searching whether a repository is Android-based more than once (this is very time-consuming)
* dates, a collection of dates that the script has already searched. If run intermittently, this prevents the same issues from being searched more than once. A date is only inserted once the entire search for that date is exhausted.

