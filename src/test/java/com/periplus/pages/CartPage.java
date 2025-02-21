package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CartPage {

    @FindBy(css = "div.row.row-cart-product")
    List<WebElement> cartItems;
    @FindBy(css = "span.nav-button-title")
    WebElement accountButton;
    @FindBy(linkText = "Logout")
    WebElement logoutButton;
    @FindBy(css = "#content p")
    WebElement logoutText;


    SoftAssert softAssert = new SoftAssert();

    public CartPage checkCartItems(WebDriver driver) {
        PageFactory.initElements(driver, this);

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
            WebElement preloader = driver.findElement(By.cssSelector(".preloader"));
            return (cartItems != null && cartItems.get(0).isDisplayed() && cartItems.get(0).isEnabled() && !preloader.isDisplayed());
        });

        softAssert.assertEquals(cartItems.size(), 1);
        softAssert.assertEquals(cartItems.get(0).findElement(
                        By.cssSelector(".product-name.limit-lines")).getText(),
                "The Lord of the Rings Illustrated by the Author: Illustrated by J.R.R. Tolkien");
//        softAssert.assertEquals(
//                cartItems.get(0).findElement(By.cssSelector("p.product-des")).getText().trim(),
//                "9780358653035");
//        softAssert.assertEquals(cartItems.get(0).findElement(By.cssSelector(".input-number.text-center")).getCssValue("value"), 1);
        softAssert.assertAll();

        return this;
    }

    public void logout(WebDriver driver) {

        Actions actions = new Actions(driver);

        actions.moveToElement(accountButton).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
            return (logoutButton != null && logoutButton.isDisplayed() && logoutButton.isEnabled());
        });

        logoutButton.click();

        wait.until(webDriver -> {
            WebElement preloader = driver.findElement(By.cssSelector(".preloader"));
            return (logoutText != null && logoutText.isDisplayed() && logoutText.isEnabled() && !preloader.isDisplayed());
        });

        assertEquals(logoutText.getText(),
                "You have been logged off your account. It is now safe to leave the computer.");
    }
}
