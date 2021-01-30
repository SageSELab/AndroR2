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
public class Script165 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.fsck.k9";
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
        UiObject2 Continue = mDevice.wait(Until.findObject(By.res("com.android.permissioncontroller", "continue_button")),2000);
        Continue.click();

        UiObject2 Ok = mDevice.wait(Until.findObject(By.res("android", "button1")),2000);
        Ok.click();

        UiObject2 Next = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "next")),2000);
        Next.click();

        UiObject2 Email = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "account_email")),2000);
        Email.setText("bugreportdataset@gmail.com");
        UiObject2 Password = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "account_password")),2000);
        Password.setText("1Password!");
         Next = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "next")),2000);
        Next.click();
        mDevice.pressBack();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UiObject2 Name = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "account_name")),2000);
        Name.setText("test");

        UiObject2 Done = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "done")),2000);
        Done.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Ok = mDevice.wait(Until.findObject(By.res("android", "button1")),2000);
        Ok.click();


        UiObject2 email = mDevice.wait(Until.findObject(By.text( "bugreportdataset@gmail.com")),2000);
        email.click();

        UiObject2 More = mDevice.wait(Until.findObject(By.desc("More options")),2000);
        More.click();



        UiObject2 Settings = mDevice.wait(Until.findObject(By.textEndsWith( "ettings")),2000);
        Settings.click();

        Settings = mDevice.wait(Until.findObject(By.textEndsWith( "Account settings")),2000);
        Settings.click();

        Settings = mDevice.wait(Until.findObject(By.textEndsWith( "Sending mail")),2000);
        Settings.click();

        Settings = mDevice.wait(Until.findObject(By.textEndsWith( "Composition defaults")),2000);
        Settings.click();

        UiObject2 Bcc = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "account_always_bcc")),2000);
        Bcc.setText("bugreportdataset@gmail.com");

        mDevice.pressBack();

        mDevice.pressBack();

        mDevice.pressBack();

        mDevice.pressBack();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UiObject2 Compose = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "compose")),2000);
        Compose.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mDevice.pressHome();
        startMainActivityFromHomeScreen();
        UiObject2 Home = mDevice.wait(Until.findObject(By.res("android", "home")),2000);
        Home.click();

        UiObject2 Drafts = mDevice.wait(Until.findObject(By.textEndsWith( "(Drafts)")),2000);
        Drafts.click();






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
