package com.codenvy.demo.concordion;


import org.concordion.ext.ScreenshotTaker;
import org.concordion.ext.ScreenshotUnavailableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.io.OutputStream;

public class SnapScreenshot implements ScreenshotTaker {

    private final WebDriver driver;

    public SnapScreenshot(WebDriver driver) {
        WebDriver baseDriver = driver;
        while (baseDriver instanceof EventFiringWebDriver) {
            baseDriver = ((EventFiringWebDriver)baseDriver).getWrappedDriver();
        }
        this.driver = baseDriver;
    }

    public int writeScreenshotTo(OutputStream outputStream) throws IOException {
        byte[] screenshot;
        try {
            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        } catch (ClassCastException e) {
            throw new ScreenshotUnavailableException("driver does not implement TakesScreenshot");
        }
        outputStream.write(screenshot);
        return ((Long)((JavascriptExecutor)driver).executeScript("return document.body.clientWidth")).intValue() + 2;
    }

    public String getFileExtension() {
        return "png";
    }
}