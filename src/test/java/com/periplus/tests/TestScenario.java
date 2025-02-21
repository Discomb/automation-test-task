package com.periplus.tests;

import com.periplus.pages.AuthPage;
import com.periplus.pages.CartPage;
import com.periplus.pages.MainPage;
import com.periplus.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestScenario {

    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    WebDriver driver = new ChromeDriver();

    @Test
    void addToCartScenario() {

        mainPage
                .openPage(driver)
                .openAuthPage(driver);

        authPage.login(driver, "n.p.malygin@gmail.com", "testpassWORD");

        mainPage.searchProductByISBN("9780358653035");

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
