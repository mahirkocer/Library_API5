package com.library.pages;

import com.github.javafaker.Faker;
import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class LibrarianEditUserPage {

    public LibrarianEditUserPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "a[href='#users']")
    public WebElement usersModule;

    @FindBy(xpath = "(//a[contains(@onclick,'Users.edit_user')])[1]")
    public WebElement editUserButton;

    @FindBy(xpath = "//a[contains(@onclick,'Users.edit_user')]")
    public List<WebElement> editUserButtons;

    public String clickRandomEditUserButton(){
        Random random = new Random();
        int randomIndex = random.nextInt(editUserButtons.size());
        String onclickAttribute = editUserButtons.get(randomIndex).getAttribute("onclick");
        String idValueAsString = onclickAttribute.substring(onclickAttribute.indexOf('(')+1,onclickAttribute.indexOf(')'));
//        int idValue = Integer.parseInt(idValueAsString);
        editUserButtons.get(randomIndex).click();
        return idValueAsString;
    }

    @FindBy(css = "input[placeholder = 'Full Name']")
    public WebElement fullNameInput;

    @FindBy(css = "input[placeholder = 'Password']")
    public WebElement passwordInput;

    @FindBy(css = "input[placeholder = 'Email']")
    public WebElement emailInput;

    @FindBy(id = "user_group_id")
    public WebElement userGroupSelect;

    @FindBy(id = "status")
    public WebElement statusSelect;

    @FindBy(name = "start_date")
    public WebElement startDateInput;

    @FindBy(name = "end_date")
    public WebElement endDateInput;

    @FindBy(id = "address")
    public WebElement addressBox;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement saveChangesButton;

    @FindBy(xpath = "//div[.='The user updated.']")
    public WebElement userUpdatedMessage;

    public void editUserFillTheFields(String fullName, String email){
        fullNameInput.clear();
        emailInput.clear();
        startDateInput.clear();
        endDateInput.clear();
        addressBox.clear();
        fullNameInput.click();
        Faker faker = new Faker();
        fullNameInput.sendKeys(fullName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(faker.bothify("########??"));
//        Select userGroup = new Select(userGroupSelect);
//        userGroup.selectByIndex(1);
//        Select status = new Select(statusSelect);
//        status.selectByIndex(1);
        startDateInput.sendKeys("2019-01-12");

        endDateInput.sendKeys("2023-05-30");

        addressBox.sendKeys(faker.address().fullAddress());
    }

}
