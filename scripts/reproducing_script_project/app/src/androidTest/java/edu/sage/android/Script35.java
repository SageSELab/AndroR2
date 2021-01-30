/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.sage.android;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import androidx.test.filters.SdkSuppress;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script35 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "me.ccrama.redditslide";
    private static final String AndroidOS = "com.android.packageinstaller";
    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testChangeText_sameActivity() {

        UiObject2 Next = mDevice.wait(Until.findObject(By.text( "GET STARTED")),2000);
        Next.click();
        Next = mDevice.wait(Until.findObject(By.text( "DONE")),2000);
        Next.click();

        UiObject2 Allow = mDevice.wait(Until.findObject(By.res(AndroidOS, "permission_allow_button")),2000);
        Allow.click();

        UiObject2 Menu = mDevice.wait(Until.findObject(By.desc( "Open")), 2000);
        Menu.click();

        //drag menu up
        UiObject2 Frontpage = mDevice.wait(Until.findObject(By.text( "frontpage")), 1000);
        Frontpage.scroll(Direction.UP, 40,400);

        UiObject2 Settings = mDevice.wait(Until.findObject(By.text( "Settings")), 1000);
        Settings.click();

        UiObject2 General = mDevice.wait(Until.findObject(By.res( BASIC_SAMPLE_PACKAGE,"general")), 1000);
        General.click();


        UiObject2 ViewType = mDevice.wait(Until.findObject(By.text( "View type")), 2000);
        ViewType.click();

        UiObject2 ViewTypeMenu = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "currentViewType")), 1000);
        ViewTypeMenu.click();

        UiObject2 CommentPane = mDevice.wait(Until.findObject(By.text( "Comment pane").res(BASIC_SAMPLE_PACKAGE, "title")), 2000);
        CommentPane.click();

        UiObject2 BackArrow = mDevice.wait(Until.findObject(By.clazz("android.widget.ImageButton")), 2000);
        BackArrow.click();
        try {
            TimeUnit.SECONDS.sleep(1);
            BackArrow = mDevice.wait(Until.findObject(By.clazz("android.widget.ImageButton")), 2000);
            BackArrow.click();

            TimeUnit.SECONDS.sleep(1);
            BackArrow = mDevice.wait(Until.findObject(By.clazz("android.widget.ImageButton")), 2000);
            BackArrow.click();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        Menu = mDevice.wait(Until.findObject(By.desc( "Open")), 3000);
        Menu.click();

        //drag menu up
        Frontpage = mDevice.wait(Until.findObject(By.text( "frontpage")), 2000);
        Frontpage.scroll(Direction.UP, 30, 400);


        Settings = mDevice.wait(Until.findObject(By.text( "Settings")), 2000);
        Settings.click();

        UiObject2 Comments = mDevice.wait(Until.findObject(By.text( "Comments")), 2000);
        Comments.click();

        UiObject2 Upvote = mDevice.wait(Until.findObject(By.text( "Show upvote percentage")), 2000);
        Upvote.scroll(Direction.DOWN, 100,300);
        //Upvote.scroll(Direction.DOWN, 100,300);
        //Upvote.scroll(Direction.DOWN, 100,300);
        //Upvote.scroll(Direction.DOWN, 100,300);


        UiObject2 Volume = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "volumenavcomments")), 1000);
        Volume.click();

        BackArrow = mDevice.wait(Until.findObject(By.clazz("android.widget.ImageButton")), 2000);
        BackArrow.click();

        BackArrow = mDevice.wait(Until.findObject(By.scrollable(true)), 2000);
        mDevice.drag(300,1500,300,300,150);
        mDevice.drag(300,1500,300,300,150);
        //BackArrow.swipe(Direction.DOWN, 0.3f);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UiObject2 About = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "about")), 2000);
        About.click();

        UiObject2 Reddit = mDevice.wait(Until.findObject(By.text( "/r/slideforreddit")), 2000);
        Reddit.click();

        UiObject2 PostWithComments = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "comments")), 1000);
        PostWithComments.click();

        mDevice.pressKeyCode(25); //Volume Down
        //mDevice.findObject(new UiSelector().description("Apps"))
        //        .click();

//        // Verify the test is displayed in the Ui
//        UiObject2 changedText = mDevice
//                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "textToBeChanged")),
//                        500 /* wait 500ms */);
//        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
