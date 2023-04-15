package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "input#inputEmail")
    public WebElement emailInput;

    @FindBy(css = "input#inputPassword")
    public WebElement passwordInput;

    @FindBy(tagName = "button")
    public WebElement signInButton;

}
