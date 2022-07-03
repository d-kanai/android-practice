package cucumber;

import com.codeborne.selenide.WebDriverProvider;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.IOSMobileCapabilityType.USE_PREBUILT_WDA;

public class AndroidDriverFactory implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(DesiredCapabilities capabilities) {
//        capabilities.setCapability("automationName", "XCUITest");
//        capabilities.setCapability("platformVersion", "15.2");
//        capabilities.setCapability("platformName", "iOS");
//        capabilities.setCapability("deviceName", "iPhone 8");
//        capabilities.setCapability("bundleId", "com.dkanai.ios-practice");
//        capabilities.setCapability("wdaStartupRetries", "10");
//        capabilities.setCapability("iosInstallPause", "8000");
//        capabilities.setCapability("wdaStartupRetryInterval", "20000");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability(APP_PACKAGE, "com.example.android_practice");
        capabilities.setCapability(APP_ACTIVITY, ".MainActivity");
        try {
//            return new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
