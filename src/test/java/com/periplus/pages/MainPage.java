package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class MainPage {

    @FindBy(css = "#nav-signin-text")
    private WebElement authButton;
    @FindBy(css = "#filter_name")
    private WebElement searchBar;


    public MainPage openPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

        driver.get("https://www.periplus.com/");
        return this;
    }

    public void openAuthPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(15, ChronoUnit.SECONDS));

        wait.until(webDriver -> {
           WebElement preloader = driver.findElement(By.cssSelector(".preloader"));
           return (authButton != null && authButton.isDisplayed() && authButton.isEnabled() && !preloader.isDisplayed());
        });

        authButton.click();
    }

    public void searchProductByISBN(String isbn) {
        searchBar.sendKeys(isbn);
        searchBar.submit();
    }

}
