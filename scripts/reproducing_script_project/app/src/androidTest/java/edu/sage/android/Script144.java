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
import androidx.test.uiautomator.Until;

import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script144 {

    private static final String BASIC_SAMPLE_PACKAGE = "com.moez.QKSMS";

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
        String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch contacts app
        Context context = getApplicationContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.android.contacts");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the contacts app to appear
        mDevice.wait(Until.hasObject(By.pkg("com.android.contacts").depth(0)), LAUNCH_TIMEOUT);

        // actions in contacts app (saving a contact: john doe)
        try {
            mDevice.findObject(new UiSelector().resourceId("com.android.contacts:id/floating_action_button")).click();
            mDevice.findObject(new UiSelector().resourceId("com.android.contacts:id/left_button")).click();
            //click
            mDevice.findObject(new UiSelector().className("android.widget.EditText").index(0)).click();
            mDevice.findObject(new UiSelector().className("android.widget.EditText").index(0)).setText("john");
            //click
            mDevice.findObject(new UiSelector().className("android.widget.EditText").index(1)).click();
            mDevice.findObject(new UiSelector().className("android.widget.EditText").index(1)).setText("doe");
            //click back
            mDevice.pressBack();
            //click
            mDevice.findObject(new UiSelector().className("android.widget.EditText").text("Phone")).click();
            mDevice.findObject(new UiSelector().className("android.widget.EditText").text("Phone")).setText("5555555555");
            mDevice.findObject(new UiSelector().resourceId("com.android.contacts:id/editor_menu_save_button")).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        // press home
        mDevice.pressHome();

        // Launch the blueprint app
        intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void performActions() throws UiObjectNotFoundException {
        // click allow
        mDevice.findObject(new UiSelector().resourceId("com.android.packageinstaller:id/permission_allow_button")).click();
        // click allow
        mDevice.findObject(new UiSelector().resourceId("com.android.packageinstaller:id/permission_allow_button")).click();
        // click yes
        mDevice.findObject(new UiSelector().resourceId("android:id/button1")).click();
        // click "+" icon
        mDevice.findObject(new UiSelector().resourceId("com.moez.QKSMS:id/compose")).click();
        // click search bar
        mDevice.findObject(new UiSelector().className("android.widget.EditText").index(0)).click();
        // type text "jo"
        mDevice.findObject(new UiSelector().className("android.widget.EditText").index(0)).setText("jo");
        // click contact "john doe"
        mDevice.findObject(new UiSelector().resourceId("com.moez.QKSMS:id/primary")).click();
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
