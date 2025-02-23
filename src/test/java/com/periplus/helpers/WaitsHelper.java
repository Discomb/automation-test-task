package com.periplus.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WaitsHelper {

    public static void waitForElementAvailability(
            WebDriver driver, WebElement webElementThatMustBeAvailable, String elementThatMustBeHiddenCSSSelector) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
            WebElement preloader = driver.findElement(By.cssSelector(elementThatMustBeHiddenCSSSelector));
            return (
                    webElementThatMustBeAvailable != null &&
                            webElementThatMustBeAvailable.isDisplayed() &&
                            webElementThatMustBeAvailable.isEnabled()
                            && !preloader.isDisplayed());
        });
    }


    public static void waitForElementAvailability(WebDriver driver, WebElement webElementThatMustBeAvailable) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> (
                webElementThatMustBeAvailable != null &&
                        webElementThatMustBeAvailable.isDisplayed() &&
                        webElementThatMustBeAvailable.isEnabled()));
    }
}
