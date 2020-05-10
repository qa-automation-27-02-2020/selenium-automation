package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by alpa on 5/10/20
 */
public class ArticleDetailsPage extends BasePage{

    private By articlePage = By.cssSelector(".article-page");

    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen() {
        return driver.findElement(articlePage).isDisplayed();
    }
}
