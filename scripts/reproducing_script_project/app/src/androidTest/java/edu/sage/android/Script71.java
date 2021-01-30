package edu.sage.android;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.filters.SdkSuppress;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script71 {
    private UiDevice mDevice;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String BASIC_SAMPLE_PACKAGE
            = "fr.neamar.kiss";

    @Before
    public void setUp() throws Exception {
        mDevice= UiDevice.getInstance(getInstrumentation());
        mDevice.pressHome();
        final String launcherPackage = getLauncherPackageName() ;
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
        Context context =getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }
    @Test
    public void UItest() throws UiObjectNotFoundException {

        //choose an app as favourite
        mDevice.findObject(new UiSelector().resourceId("fr.neamar.kiss.debug:id/searchEditText")).setText("Google");
        //click google
        mDevice.findObject(new UiSelector().clickable(true).className("android.widget.LinearLayout").index(1)).click();
        //press go back
        mDevice.pressBack();
        //go to menu
        mDevice.findObject(new UiSelector().resourceId("fr.neamar.kiss.debug:id/menuButton")).click();
        //GUI actions
        mDevice.findObject(new UiSelector().className("android.widget.TextView").index(0).text("KISS settings")).click();
        mDevice.findObject(new UiSelector().text("User experience")).click();
        mDevice.findObject(new UiSelector().className("android.widget.Switch").instance(2)).click();
        mDevice.findObject(new UiSelector().className("android.widget.Switch").instance(4)).click();
        mDevice.pressHome();

    }

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
