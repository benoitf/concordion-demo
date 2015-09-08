package com.codenvy.demo.concordion;

import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Florent Benoit
 */
public class AbsConcordion {

    @Extension
    public ConcordionExtension extension = new CustomExtension().setSeleniumDriver(driver);

    private static FirefoxDriver driver;


    @BeforeClass
    public static void startDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
    }

    public FirefoxDriver getDriver() {
        return driver;
    }


    protected void waitLoadPage() {
        try {
            new WebDriverWait(getDriver(), 5).until(waiting(By.id("waiting")));
        } catch (Exception e) {

        }

    }

    public static ExpectedCondition<WebElement> waiting(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public ExpectedCondition<WebElement> visibilityOfElementLocated = ExpectedConditions.visibilityOfElementLocated(locator);

            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = visibilityOfElementLocated.apply(driver);
                try {
                    if (element != null && !element.getAttribute("class").contains("waiting")) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "waiting...";
            }
        };
    }

}
