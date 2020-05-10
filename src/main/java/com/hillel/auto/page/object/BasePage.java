package com.hillel.auto.page.object;

import com.hillel.auto.WaitsExample;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by alpa on 5/10/20
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WaitsExample waits;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitsExample(driver);;
    }

    protected void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void inputText(By locator, String text) {
        WebElement element = waits.visibilityOfElementLocated(locator);
        element.clear();
        element.sendKeys(text);
    }
}
