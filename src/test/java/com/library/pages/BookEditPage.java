package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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



}
