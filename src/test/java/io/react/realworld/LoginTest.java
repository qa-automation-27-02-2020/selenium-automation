package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.page.object.HomePage;
import com.hillel.auto.page.object.LoginPage;
import com.hillel.auto.utils.UserData;
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

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUserName())).isTrue();
    }

}
