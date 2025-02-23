package com.periplus.tests;

import com.periplus.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestScenario {

    private final String LOGIN = "n.p.malygin@gmail.com";
    private final String PASSWORD = "testpassWORD";
    private final String ISBN = "9780358653035";

    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    AccountPage accountPage = new AccountPage();

    WebDriver driver = new ChromeDriver();

    @Test(description = "Adding a book to the cart")
    void addToCartScenario() {

        mainPage
                .openPage(driver)
                .openAuthPage(driver);

        authPage.login(driver, LOGIN, PASSWORD);

        accountPage.searchProductByISBN(driver, ISBN);

        productPage
                .checkBookName(driver)
                .addToCart()
                .checkPopupMessage(driver)
                .checkCartCounter()
                .goToCart(driver);

        cartPage
                .checkCartItems(driver);
    }

    @AfterClass
    void tearDown() {
        cartPage.logout(driver);
        driver.quit();
    }
}
