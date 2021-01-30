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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script10 {

    private static final String PACKAGE_NAME
            = "me.zhanghai.android.files";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice uiDevice;

    @Before
    public void startActivityFromHomeScreen() {
        // Initialize UiDevice instance
        uiDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        uiDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        uiDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void performGUIActionsAndAssertions()
            throws UiObjectNotFoundException, RemoteException, InterruptedException {
        // Setup GUI actions
        // Press allow button
        uiDevice.findObject(new UiSelector().className("android.widget.Button").instance(1)).click();

        // Bug-specific GUI actions
        // Press menu option
        uiDevice.findObject(new UiSelector().clickable(true)).click();
        // Press FTP server option
        uiDevice.findObject(new UiSelector().text("FTP server")).click();
        // Toggle on status button
        uiDevice.findObject(new UiSelector().clickable(true).instance(1)).click();
        // Toggle off status button
        uiDevice.findObject(new UiSelector().clickable(true).instance(1)).click();
        // Press recent apps button
        uiDevice.pressRecentApps();
        // Remove app from recent apps
        TimeUnit.MILLISECONDS.sleep(1000);
        uiDevice.swipe(100, 1000, 1000, 1000, 10);
        // Reopen app
        startActivityFromHomeScreen();
        // Press menu option
        uiDevice.findObject(new UiSelector().clickable(true)).click();
        // Press FTP server option
        uiDevice.findObject(new UiSelector().text("FTP server")).click();

        // Assertions
        // Assert status is toggled
        assertTrue(uiDevice.findObject(new UiSelector().className("android.widget.Switch")).isChecked());
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
