package io.react.realworld;

import com.hillel.auto.User;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 5/7/20
 */
public class LoginTest extends TestBase {

    private User user = UserData.defaultUser();

    @Test
    public void loginTest() {
        clickLoginButton();

        checkPage("Sign In");

        inputText(emailField(), user.getEmail());
        inputText(passwordField(), user.getPassword());

        clickSingInButton();

        userShouldBeLoggedIn(user.getUserName());

    }

    private void clickLoginButton() {
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#login']"));
        signUpButton.click();
    }

}
