package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by alpa on 5/10/20
 */
public class ProfilePage extends BasePage {

    private By articles = By.cssSelector(".article-preview");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public int getArticlesSize() {
        return driver.findElements(articles).size();
    }
}
