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
import android.os.RemoteException;

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

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script43 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "it.feio.android.omninotes";
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

        UiObject2 Next = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "next")),2000);
        Next.click();
        Next.click();
        Next.click();
        Next.click();
        Next.click();
        Next = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "done")),2000);
        Next.click();

        UiObject2 Ok = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "buttonDefaultPositive")), 2000);
        Ok.click();

        UiObject2 Hamburger = mDevice.wait(Until.findObject(By.clazz( "android.widget.ImageButton")),2000);
        Hamburger.click();

        UiObject2 Settings = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "settings")),2000);
        Settings.click();

        UiObject2 Data = mDevice.wait(Until.findObject(By.text( "Data")),2000);
        Data.click();
        UiObject2 Password = mDevice.wait(Until.findObject(By.text( "Password")),2000);
        Password.click();

        UiObject2 Password1 = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "password")),2000);
        Password1.setText("password");

        UiObject2 Password2 = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "password_check")),2000);
        Password2.setText("password");

        UiObject2 Question = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "question")),2000);
        Question.setText("a");

        UiObject2 Answer = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "answer")),2000);
        Answer.setText("b");

        UiObject2 AnswerCheck = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "answer_check")),2000);
        AnswerCheck.setText("b");

        AnswerCheck = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "answer_check")),2000);
        AnswerCheck.scroll(Direction.DOWN, 100, 300);



        UiObject2 Confirm = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "password_confirm")),2000);
        Confirm.click();

        /* request = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "password_request")),2000);
        request.setText("password");
        Ok = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "buttonDefaultPositive")), 2000);
        Ok.click();*/

        UiObject2 request = mDevice.wait(Until.findObject(By.text( "Request password on access")),2000);
        request.click();

        request = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "password_request")),2000);
        request.setText("password");
        Ok = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "buttonDefaultPositive")), 2000);
        Ok.click();

        mDevice.pressHome();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            mDevice.pressRecentApps();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UiObject2 Dismiss = mDevice.wait(Until.findObject(By.desc( "Dismiss Omni Notes.")),2000);
        Dismiss.click();
        startMainActivityFromHomeScreen();
        Ok = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "buttonDefaultNeutral")), 2000);
        Ok.click();




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
