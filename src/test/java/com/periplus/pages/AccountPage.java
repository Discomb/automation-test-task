package com.periplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    @FindBy(css = "#filter_name")
    private WebElement searchBar;

    public void searchProductByISBN(WebDriver driver, String isbn) {
        PageFactory.initElements(driver, this);

        searchBar.sendKeys(isbn);
        searchBar.submit();
    }
}
