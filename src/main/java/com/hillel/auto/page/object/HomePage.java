package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alpa on 5/10/20
 */
public class HomePage extends BasePage {

    private By profileBtn = By.xpath("//*[@class='user-pic']/..");

    public HomePage (WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn(String userName) {
        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        return userInfo.isDisplayed();
    }

    public NewArticlePage clickNewPost() {
        driver.findElement(By.cssSelector("[href='#editor']")).click();
        return new NewArticlePage(driver);
    }

    public ProfilePage clickProfile() {
        driver.findElement(profileBtn).click();
        return new ProfilePage(driver);
    }


}
