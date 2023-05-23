package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookEditPage extends BasePage{


@FindBy(xpath = "//tr//td//a")
    public WebElement editButton;

@FindBy(xpath = "//input[@name=\'year\']")
    public WebElement year;

    @FindBy(xpath = "//input[@name=\'name\']")
    public WebElement name;

    @FindBy(xpath = "//input[@name=\'isbn\']")
    public WebElement isbn;

    @FindBy(xpath = "//input[@name=\'author\']")
    public WebElement author;

    @FindBy(xpath = "//input[@name=\'description\']")
    public WebElement description;
@FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement saveButton;

@FindBy(xpath = "(//div[.='The book has been updated.'])[3]")
    public WebElement savedBookWrite;
@FindBy(xpath = "//label[@class='control-label']")
    public List<WebElement> parameters;

@FindBy(id = "book_categories")
    public WebElement bookCathagories;
@FindBy(xpath = "//tbody/tr/td[5]")
    public List<WebElement> bookCathegoriName;
@FindBy(xpath = "//a[@class='page-link']")
    public List<WebElement> pageNumber;
@FindBy(xpath = "//a[@title='Next']")
    public WebElement nextButton;

}
