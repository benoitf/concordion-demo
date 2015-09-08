package com.codenvy.demo.concordion;


import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.ScreenshotExtension;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CustomExtension implements ConcordionExtension {

    ScreenshotExtension screenshotExtension = new ScreenshotExtension().setScreenshotOnAssertionSuccess(true);

    public CustomExtension setSeleniumDriver(FirefoxDriver driver) {
        screenshotExtension.setScreenshotTaker(new SnapScreenshot(driver));
        return this;
    }

    public void addTo(ConcordionExtender concordionExtender) {
        screenshotExtension.addTo(concordionExtender);
    }
}