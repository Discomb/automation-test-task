package com.periplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import static com.periplus.entities.constants.CSSSelectors.PRELOADER;
import static com.periplus.helpers.WaitsHelper.waitForElementAvailability;
import static org.testng.Assert.assertEquals;

public class ProductPage {

    private final String NOTIFICATION_MODAL_CSS_SELECTOR = "#Notification-Modal";
    private final String EXPECTED_BOOK_TITLE =
            "The Lord of the Rings Illustrated by the Author: Illustrated by J.R.R. Tolkien";
    private final String EXPECTED_SUCCESS_MESSAGE = "Success add to cart";
    private final int EXPECTED_BOOK_COUNT = 1;
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

        waitForElementAvailability(driver, bookTitle, PRELOADER);

        assertEquals(bookTitle.getText(), EXPECTED_BOOK_TITLE);

        return this;
    }

    public ProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    public ProductPage checkPopupMessage(WebDriver driver) {
        waitForElementAvailability(driver, modalText);

        softAssert.assertEquals(modalText.getText(), EXPECTED_SUCCESS_MESSAGE);
        softAssert.assertTrue(modalCheckMark.isDisplayed());
        softAssert.assertAll();

        return this;
    }

    public ProductPage checkCartCounter() {
        assertEquals(cartCounter.getText(), String.valueOf(EXPECTED_BOOK_COUNT));

        return this;
    }

    public void goToCart(WebDriver driver) {
        waitForElementAvailability(driver, cart,
                NOTIFICATION_MODAL_CSS_SELECTOR);
        cart.click();
    }
}
