package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class US005memoPage {
    public US005memoPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//ul/li//a)[2]")
    public WebElement usersLink;

    @FindBy(css = "a.btn.btn-lg.btn-outline.btn-primary.btn-sm")
    public WebElement addUserLink;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullnameField;

    public String email;
    public static String addedName = "mylibDeleteme";
    public static String createdmail = "librarian111@library";
    public static String addedId = "10261";

    public static String query_getMylastAddedid = "select * from users order by id desc limit 1";

    public static String query_user_groups = "select group_name from user_groups";
}
