package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.page.object.ArticleDetailsPage;
import com.hillel.auto.page.object.HomePage;
import com.hillel.auto.page.object.LoginPage;
import com.hillel.auto.page.object.NewArticlePage;
import com.hillel.auto.page.object.ProfilePage;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 5/10/20
 */
public class ArticleTest extends TestBase {

    private User user = UserData.defaultUser();
    private HomePage homePage;

    @BeforeClass
    public void login() {
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        homePage = loginPage.login(user.getEmail(), user.getPassword());
        assertThat(homePage.isUserLoggedIn(user.getUserName())).isTrue();
    }

    @Test
    public void createArticle() {
        NewArticlePage newArticlePage = homePage.clickNewPost();
        newArticlePage.inputArticleTitle("blabla");
        newArticlePage.inputWhatArticleAbout("about blabla");
        newArticlePage.inputArticle("trololo");
        newArticlePage.inputTags("blabla");

        ArticleDetailsPage articleDetailsPage = newArticlePage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.isPageOpen()).isTrue();
    }

    @Test
    public void checkArticleSize() {
        ProfilePage profilePage = homePage.clickProfile();
        assertThat(profilePage.getArticlesSize()).isGreaterThan(2);
    }


}
