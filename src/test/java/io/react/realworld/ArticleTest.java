package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.page.object.ArticleDetailsPage;
import com.hillel.auto.page.object.HomePage;
import com.hillel.auto.page.object.LoginPage;
import com.hillel.auto.page.object.NewArticlePage;
import com.hillel.auto.page.object.ProfilePage;
import com.hillel.auto.utils.UserData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();
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
