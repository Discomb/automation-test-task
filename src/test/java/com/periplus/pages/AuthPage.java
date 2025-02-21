package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class AuthPage {

    @FindBy(css = "input[type=email]")
    WebElement emailInput;
    @FindBy(css = "#ps")
    WebElement passwordInput;
    @FindBy(css = "#button-login")
    WebElement loginButton;

    public void login(WebDriver driver, String login, String pass) {
        PageFactory.initElements(driver, this);

        emailInput.sendKeys(login);
        passwordInput.sendKeys(pass);
        loginButton.click();
    }
}
