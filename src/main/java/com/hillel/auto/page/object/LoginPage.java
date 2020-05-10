package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alpa on 5/10/20
 */
public class LoginPage extends BasePage {

    public LoginPage (WebDriver driver) {
        super(driver);
    }


    public HomePage login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        return clickSingInButton();
    }

    public void inputEmail(String email) {
        WebElement emailField = singForm().findElement(By.cssSelector("input[type='email']"));
        inputText(emailField, email);
    }

    public void inputPassword(String password) {
        WebElement passwordField = singForm().findElement(By.cssSelector("input[type='password']"));
        inputText(passwordField, password);
    }

    public String getPageTitle() {
       return driver.findElement(By.cssSelector(".auth-page h1")).getText();
    }

    public HomePage clickSingInButton() {
        WebElement signInButton = singForm().findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();
        return new HomePage(driver);
    }

    protected WebElement singForm() {
        return driver.findElement(By.cssSelector(".auth-page form"));
    }



}
