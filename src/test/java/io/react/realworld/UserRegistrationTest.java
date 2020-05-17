package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 5/3/20
 */
public class UserRegistrationTest extends TestBase {

    @Test
    public void registrationTest() {
        clickRegistrationButton();

        checkPage("Sign Up");

        assertThat(singForm().isDisplayed()).isTrue();

        User user = UserData.randomUser();

        inputText(userNameField(), user.getUsername());
        inputText(emailField(), user.getEmail());
        inputText(passwordField(), user.getPassword());

        clickSingInButton();

        userShouldBeLoggedIn(user.getUsername());
    }

    @Test
    public void validateRegistrationForm() {
        clickRegistrationButton();

        checkPage("Sign Up");

        clickSingInButton();

        List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));

//        assertThat(errorsMessage.get(0).getText()).isEqualTo("email can't be blank");
//        assertThat(errorsMessage.get(1).getText()).isEqualTo("password can't be blank");
//        assertThat(errorsMessage.get(2).getText()).isEqualTo("username can't be blankis too short (minimum is 1 character)is too long (maximum is 20 characters)");

//        List<String> errors = new ArrayList<>();
//        for (WebElement error : errorsMessage) {
//            errors.add(error.getText());
//        }
        List<String> errors = errorsMessage.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        assertThat(errors).hasSize(3);

        assertThat(errors).contains("email can't be blank",
                "password can't be blank",
                "username can't be blankis too short (minimum is 1 character)is too long (maximum is 20 characters)");
    }


    private void clickRegistrationButton() {
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();
    }

    private WebElement userNameField() {
        return singForm().findElement(By.cssSelector("input[type='text']"));
    }
}
