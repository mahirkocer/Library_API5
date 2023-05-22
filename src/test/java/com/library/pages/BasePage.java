package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {



    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//span[.='Dashboard']")
    public WebElement dashBoard;
    @FindBy(xpath = "//span[.='Books']")
    public WebElement books;
    @FindBy(xpath = "//span[.='Users']")
    public WebElement users;

@FindBy(xpath = "//span[contains(.,'Test Librarian')]")
    public  WebElement login;


}
