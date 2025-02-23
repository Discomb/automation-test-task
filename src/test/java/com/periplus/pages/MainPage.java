package com.periplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.periplus.entities.constants.CSSSelectors.PRELOADER;
import static com.periplus.helpers.WaitsHelper.waitForElementAvailability;

public class MainPage {

    private final String MAIN_PAGE_URL = "https://www.periplus.com/";

    @FindBy(css = "#nav-signin-text")
    private WebElement authButton;
    @FindBy(css = "#filter_name")
    private WebElement searchBar;


    public MainPage openPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

        driver.get(MAIN_PAGE_URL);

        return this;
    }

    public void openAuthPage(WebDriver driver) {
        waitForElementAvailability(driver, authButton, PRELOADER);
        authButton.click();
    }

    public void searchProductByISBN(String isbn) {
        searchBar.sendKeys(isbn);
        searchBar.submit();
    }

}
