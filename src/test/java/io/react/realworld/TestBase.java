package io.react.realworld;

import com.hillel.auto.config.WebDriverConfig;
import com.hillel.auto.config.WebDriverFactory;
import com.hillel.auto.listener.TestMethodExecutionListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 5/7/20
 */
@Listeners({TestMethodExecutionListener.class})
public class TestBase {

    protected WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverConfig.load();
    }

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.get("https://react-redux.realworld.io/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    protected void checkPage(String pageHeader) {
        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo(pageHeader);
    }

    protected WebElement singForm() {
        return driver.findElement(By.cssSelector(".auth-page form"));
    }

    protected WebElement emailField() {
        return singForm().findElement(By.cssSelector("input[type='email']"));
    }

    protected WebElement passwordField() {
        return singForm().findElement(By.cssSelector("input[type='password']"));
    }

    protected void clickSingInButton() {
        WebElement signInButton = singForm().findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();
    }

    protected void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void userShouldBeLoggedIn(String userName) {
        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        assertThat(userInfo.isDisplayed()).isTrue();
    }

    protected void clickLoginButton() {
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#login']"));
        signUpButton.click();
    }

}
