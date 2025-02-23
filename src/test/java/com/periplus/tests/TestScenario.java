package com.periplus.tests;

import com.periplus.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestScenario {

    private final String LOGIN = "n.p.malygin@gmail.com";
    private final String PASSWORD = "testpassWORD";
    private final String ISBN = "9780358653035";
    private final String EXPECTED_BOOK_TITLE =
            "The Lord of the Rings Illustrated by the Author: Illustrated by J.R.R. Tolkien";
    private final String EXPECTED_SUCCESS_MESSAGE = "Success add to cart";
    private final int EXPECTED_BOOK_COUNT = 1;
    private final String EXPECTED_LOGOUT_TEXT =
            "You have been logged off your account. It is now safe to leave the computer.";

    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    AccountPage accountPage = new AccountPage();

    @BeforeClass
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
    }

    WebDriver driver = new ChromeDriver();

    @Test(description = "Adding a book to the cart")
    void addToCartScenario() {

        mainPage
                .openPage(driver)
                .goToAuthPage(driver);

        authPage.login(driver, LOGIN, PASSWORD);

        accountPage.searchProductByISBN(driver, ISBN);

        productPage
                .checkBookName(driver, EXPECTED_BOOK_TITLE)
                .addToCart()
                .checkPopupMessage(driver, EXPECTED_SUCCESS_MESSAGE)
                .checkCartCounter(EXPECTED_BOOK_COUNT)
                .goToCart(driver);

        cartPage
                .checkCartItems(driver, ISBN, EXPECTED_BOOK_TITLE, EXPECTED_BOOK_COUNT);
    }

    @AfterClass
    void tearDown() {
        cartPage.logout(driver, EXPECTED_LOGOUT_TEXT);
        driver.quit();
    }
}
