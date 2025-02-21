package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPage {

    SoftAssert softAssert = new SoftAssert();

    @FindBy(css = "h2")
    WebElement bookTitle;
    @FindBy(css = "button.btn.btn-add-to-cart")
    WebElement addToCartButton;
    @FindBy(css = "div.modal-text")
    WebElement modalText;
    @FindBy(css = "div.ti-check.modal-check")
    WebElement modalCheckMark;
    @FindBy(css = "#cart_total")
    WebElement cartCounter;
    @FindBy(css = "#show-your-cart")
    WebElement cart;

    public ProductPage checkBookName(WebDriver driver) {
        PageFactory.initElements(driver, this);

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
            WebElement preloader = driver.findElement(By.cssSelector(".preloader"));
            return (bookTitle != null && bookTitle.isDisplayed() && bookTitle.isEnabled() && !preloader.isDisplayed());
        });

        assertEquals(bookTitle.getText(),
                "The Lord of the Rings Illustrated by the Author: Illustrated by J.R.R. Tolkien");

        return this;
    }

    public ProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    public ProductPage checkPopupMessage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
            return (modalText != null && modalText.isDisplayed() && modalText.isEnabled());
        });

        softAssert.assertEquals(modalText.getText(), "Success add to cart");
        softAssert.assertTrue(modalCheckMark.isDisplayed());
        softAssert.assertAll();

        return this;
    }

    public ProductPage checkCartCounter() {
        assertEquals(cartCounter.getText(), "1");

        return this;
    }

    public void goToCart(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
            WebElement notificationModal = driver.findElement(By.cssSelector("#Notification-Modal"));
            return (cart != null && cart.isDisplayed() && cart.isEnabled() && !notificationModal.isDisplayed());
        });

        cart.click();
    }
}
