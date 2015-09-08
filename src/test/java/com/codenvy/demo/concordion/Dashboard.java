package com.codenvy.demo.concordion;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

@RunWith(ConcordionRunner.class)
public class Dashboard extends AbsConcordion {

    private WebElement passwordField;

    public String go(String url) {
        getDriver().get(url);

        // wait
        waitLoadPage();

        return "dashboard page";
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

}