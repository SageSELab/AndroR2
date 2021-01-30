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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import java.util.concurrent.TimeUnit;
/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script62 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "swati4star.createpdf";

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

        UiObject2 AddImages = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "addImages")), 2000);
        AddImages.click();

        UiObject2 TakePicture = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "btn_take_picture")), 3000);
        TakePicture.click();
        UiObject2 TakePicture2 = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "btn_take_picture")), 3000);
        TakePicture2.click();
        UiObject2 TakePicture3 = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "btn_take_picture")), 3000);


        TakePicture3.click();

        UiObject2 Done = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "action_done")), 2000);
        Done.click();

        UiObject2 Crop = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "cropImages")), 1000);
        Crop.click();

        UiObject2 CropImage = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "crop_image_menu_crop")), 1000);
        CropImage.click();

        UiObject back = mDevice.findObject(new UiSelector().className("android.widget.ImageButton").instance(0));
        try {
            back.click();
            back.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("peepeepoopoo");
            e.printStackTrace();
        }

        UiObject2 create = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "pdfCreate")), 1000);
        create.click();

        UiObject2 TextInput = mDevice.wait(Until.findObject(By.res("android", "input")), 1000);
        TextInput.setText("Test");

        UiObject2 Ok = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "md_buttonDefaultPositive")), 1000);
        Ok.click();

        UiObject2 Open = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "pdfOpen")), 1000);
        Open.click();

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
