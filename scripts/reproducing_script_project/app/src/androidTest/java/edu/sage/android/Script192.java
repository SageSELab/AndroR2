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
public class Script192 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "net.gsantner.markor";
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
    public void testChangeText_sameActivity() throws RemoteException {
        UiObject2 Ok = mDevice.wait(Until.findObject(By.res("android", "button1")), 2000);
        Ok.click();

        UiObject2 Plus = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab_add_new_item")), 2000);


        Plus.click();

        UiObject2 Allow = mDevice.wait(Until.findObject(By.res(AndroidOS, "permission_allow_button")),2000);


        Allow.click();

        UiObject2 Plus2 = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab_add_new_item")), 2000);

        Plus2.click();
        UiObject2 Title = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "note__activity__edit_note_title")),2000);
        Title.setText("test");

        UiObject2 Text = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "document__fragment__edit__highlighting_editor"));
        Text.setText("12345");

        UiObject back = mDevice.findObject(new UiSelector().className("android.widget.ImageButton").instance(0));
        try {
            back.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }


        UiObject2 FirstNote = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "note_title"));
        FirstNote.click();
        UiObject2 Text2 = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "document__fragment__edit__highlighting_editor")),2000);
        Text2.click();

        // Start from the home screen
        mDevice.pressRecentApps();
        //The button is pressed four times on Android 7 to cycle from Markor to the recent menu, then to the previous app, then back to the recent menu, then back to markor.
        try {
            TimeUnit.SECONDS.sleep(1);
            mDevice.pressRecentApps();
            TimeUnit.SECONDS.sleep(1);
            mDevice.pressRecentApps();
            TimeUnit.SECONDS.sleep(1);
            mDevice.pressRecentApps();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
