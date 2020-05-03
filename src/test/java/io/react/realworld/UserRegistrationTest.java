package io.react.realworld;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 5/3/20
 */
public class UserRegistrationTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registrationTest() {
        driver.get("https://react-redux.realworld.io/");
//        #main > div > nav > div > ul > li:nth-child(3) > a
//        WebElement signUpButton = driver.findElement(By.cssSelector("#main > div > nav > div > ul > li:nth-child(3) > a"));
//        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();

//        String currentUrl = driver.getCurrentUrl();
//        assertThat(currentUrl).contains("register");
        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo("Sign Up");

        WebElement singUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singUpForm.isDisplayed()).isTrue();

        String userName = "realapp" + new Random().nextInt(10000);
        String email = userName + "@mail.com";
        String password = "qwerty123";

        WebElement userNameField = singUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = singUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        assertThat(userInfo.isDisplayed()).isTrue();
    }

    @Test
    public void loginTest() {
        String user = "realapp";
        String email = "realapp@mail.com";
        String password = "qwerty123";

    }


}
