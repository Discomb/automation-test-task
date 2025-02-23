package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.periplus.entities.constants.CSSSelectors.PRELOADER;
import static com.periplus.helpers.WaitsHelper.waitForElementAvailability;
import static org.testng.Assert.assertEquals;


public class CartPage {

    private final String EXPECTED_LOGOUT_TEXT =
            "You have been logged off your account. It is now safe to leave the computer.";
    private final String EXPECTED_BOOK_TITLE =
            "The Lord of the Rings Illustrated by the Author: Illustrated by J.R.R. Tolkien";
    private final String BOOK_TITLE_CSS_SELECTOR = ".product-name.limit-lines";

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

        waitForElementAvailability(driver, cartItems.get(0), PRELOADER);

        softAssert.assertEquals(cartItems.size(), 1);
        softAssert.assertEquals(cartItems.get(0).findElement(
                        By.cssSelector(BOOK_TITLE_CSS_SELECTOR)).getText(),
                EXPECTED_BOOK_TITLE);
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
        waitForElementAvailability(driver, logoutButton);
        logoutButton.click();
        waitForElementAvailability(driver, logoutText, PRELOADER);

        assertEquals(logoutText.getText(),
                EXPECTED_LOGOUT_TEXT);
    }
}
