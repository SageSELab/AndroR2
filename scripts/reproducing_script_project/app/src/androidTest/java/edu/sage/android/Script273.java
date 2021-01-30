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
public class Script273 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.ichi2.anki";
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

        //UiObject2 OK = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "md_buttonDefaultPositive")),2000);
        //OK.click();
        UiObject2 Allow = mDevice.wait(Until.findObject(By.res("com.android.packageinstaller", "permission_allow_button")),2000);
        Allow.click();



        UiObject2 Plus = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab_expand_menu_button")),2000);
        Plus.click();





        UiObject2 Add = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "add_note_action")),2000);
        Add.click();

        UiObject2 Front = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "id_note_editText")),2000);
        Front.setText("a");


        UiObject2 Save = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "action_save")),2000);
        Save.click();

        Front = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "id_note_editText")),2000);
        Front.setText("b");


        Save = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "action_save")),2000);
        Save.click();

        UiObject2 Back = mDevice.wait(Until.findObject(By.desc( "Navigate up")),2000);
        Back.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UiObject2 Hamburger = mDevice.wait(Until.findObject(By.desc( "Navigate up")),2000);
        Hamburger.click();

        UiObject2 CardBrowser = mDevice.wait(Until.findObject(By.text( "Card browser")),2000);
        CardBrowser.click();


        //Click on the options menu
        mDevice.click(1023, 131);



        UiObject2 Preview = mDevice.wait(Until.findObject(By.text( "Preview")),2000);
        Preview.click();


        UiObject2 next = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "nextTime2")),2000);
        next.click();
        next.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            mDevice.setOrientationLeft();

        } catch (RemoteException e) {
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
