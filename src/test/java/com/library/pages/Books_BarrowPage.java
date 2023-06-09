package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Books_BarrowPage {
    public Books_BarrowPage() {
       PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[.='Books']")
    public WebElement booksBtn;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchBox;

    @FindBy(xpath = "//td[.='Diceros bicornis']/preceding-sibling::td[2]")
    public WebElement borrowBook;

    @FindBy(id = "toast-container")
    public WebElement successMessage;

    @FindBy(xpath = "(//td//a)[last()]")
    public WebElement unBarrowBtn;

    @FindBy(xpath = "//*[@id=\"menu_item\"]/li[2]/a/span[1]")
    public WebElement BarrowedBoooks;

}
