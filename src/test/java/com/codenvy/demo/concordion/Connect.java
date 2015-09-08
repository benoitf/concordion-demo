package com.codenvy.demo.concordion;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@RunWith(ConcordionRunner.class)
public class Connect extends AbsConcordion {

    private WebElement passwordField;

    public String login(String username, String password, String codenvyURL) {
        getDriver().get(codenvyURL);
        WebElement emailField = getDriver().findElement(By.id("emailid"));
        passwordField = getDriver().findElement(By.cssSelector(".signup-page input[type='password']"));

        emailField.sendKeys(username);
        passwordField.sendKeys(password);

        return "fill user";
    }

    public String submit() {
        passwordField.submit();
        // wait
        waitLoadPage();
        return "submit";
    }












}