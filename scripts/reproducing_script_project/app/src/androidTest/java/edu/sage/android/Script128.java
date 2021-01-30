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
import android.graphics.Point;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import androidx.test.filters.SdkSuppress;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script128 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "org.shadowice.flocke.andotp.dev";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String PASSWORD_TO_BE_TYPED = "abcd1234";

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
    public void performActions() throws UiObjectNotFoundException {
        //Click Next
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/mi_button_next")).click();
        //Click Next
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/mi_button_next")).click();
        //Type password
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/introPasswordEdit")).setText(PASSWORD_TO_BE_TYPED);
        //Click confirm password
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/introPasswordConfirm")).click();
        //Type confirm password
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/introPasswordConfirm")).setText(PASSWORD_TO_BE_TYPED);
        //Click Next
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/mi_button_next")).click();
        //Click Finished
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/mi_button_next")).clickAndWaitForNewWindow();
        //Type password
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/passwordEdit")).setText(PASSWORD_TO_BE_TYPED);
        //Click unlock button
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/buttonUnlock")).clickAndWaitForNewWindow();
        //Click OK
        mDevice.findObject(new UiSelector().clickable(true).instance(0)).click();


        //Click Menu
        mDevice.findObject(new UiSelector().clickable(true).instance(3)).click();
        //Click Settings
        mDevice.findObject(new UiSelector().resourceId("org.shadowice.flocke.andotp.dev:id/title").text("Settings")).click();
        //Click Language
        UiScrollable settingsView = new UiScrollable(new UiSelector().scrollable(true));
        settingsView.scrollIntoView(new UiSelector().text("Language"));
        mDevice.findObject(new UiSelector().text("Language")).click();
        //Click French
        mDevice.findObject(new UiSelector().className("android.widget.CheckedTextView").index(7)).click();
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
