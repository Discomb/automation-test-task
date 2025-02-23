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

    private final String BOOK_TITLE_CSS_SELECTOR = ".product-name.limit-lines";

    @FindBy(css = "div.row.row-cart-product")
    List<WebElement> cartItems;
    @FindBy(css = "span.nav-button-title")
    WebElement accountButton;
    @FindBy(linkText = "Logout")
    WebElement logoutButton;
    @FindBy(css = "#content p")
    WebElement logoutText;
    @FindBy(css = ".btn.btn-cart-remove")
    WebElement removeButton;
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]")
    WebElement emptyCartMessage;


    SoftAssert softAssert = new SoftAssert();

    public CartPage checkCartItems(WebDriver driver, String isbn, String expectedBookTitle, int expectedBookCount) {
        PageFactory.initElements(driver, this);

        waitForElementAvailability(driver, cartItems.get(0), PRELOADER);

        softAssert.assertEquals(cartItems.size(), expectedBookCount);
        softAssert.assertEquals(cartItems.get(0).findElement(
                        By.cssSelector(BOOK_TITLE_CSS_SELECTOR)).getText(),
                expectedBookTitle);
        softAssert.assertEquals(
                cartItems.get(0).findElement(
                                By.xpath("//*[@id=\"basket\"]/div/div/div/div/div/div/div[1]/div[2]/div[2]"))
                        .getText().trim(),
                isbn);
        softAssert.assertEquals(cartItems.get(0)
                .findElement(By.cssSelector("input.input-number.text-center"))
                .getAttribute("value"), String.valueOf(expectedBookCount));
        softAssert.assertAll();

        return this;
    }

    public CartPage removeItem(WebDriver driver) {
        removeButton.click();

        waitForElementAvailability(driver, emptyCartMessage, PRELOADER);

        assertEquals(emptyCartMessage.getText(), "Your shopping cart is empty");

        return this;
    }

    public void logout(WebDriver driver, String expectedLogoutText) {

        Actions actions = new Actions(driver);

        actions.moveToElement(accountButton).perform();
        waitForElementAvailability(driver, logoutButton);
        logoutButton.click();
        waitForElementAvailability(driver, logoutText, PRELOADER);

        assertEquals(logoutText.getText(),
                expectedLogoutText);
    }
}
