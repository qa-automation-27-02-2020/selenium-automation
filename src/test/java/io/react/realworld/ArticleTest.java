package io.react.realworld;

import com.hillel.auto.model.Article;
import com.hillel.auto.model.User;
import com.hillel.auto.page.object.ArticleDetailsPage;
import com.hillel.auto.page.object.HomePage;
import com.hillel.auto.page.object.LoginPage;
import com.hillel.auto.page.object.NewArticlePage;
import com.hillel.auto.page.object.ProfilePage;
import com.hillel.auto.service.ArticleService;
import com.hillel.auto.service.UserService;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 5/10/20
 */
public class ArticleTest extends TestBase {

    private UserService userService = new UserService();
    private ArticleService articleService;
    private User user;
    private Article article;
    private HomePage homePage;

    @BeforeMethod
    public void login() {
        user = userService.userRegistration();
        articleService = new ArticleService(user.getToken());

        article = articleService.createArticle(getArticle());

//        Cookie cookie = new Cookie("Authorization", "Token " + user.getToken());
//        driver.manage().addCookie(cookie);
//        driver.navigate().refresh();

        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        homePage = loginPage.login(user.getEmail(), user.getPassword());
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();
    }

    @AfterMethod
    public void cleanData() {
        articleService.deleteArticle(article.getSlug());
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
    public void editArticleTest() {
        ProfilePage profilePage = homePage.clickProfile();
        List<String> allArticleTitles = profilePage.getAllArticleTitles();
        assertThat(allArticleTitles).contains(article.getTitle());
//        TODO add edit steps
    }


    @Test
    public void checkArticleSize() {
        ProfilePage profilePage = homePage.clickProfile();
        assertThat(profilePage.getArticlesSize()).isGreaterThan(2);
    }

    private Article getArticle() {
        Article article = new Article();
        article.setTitle("First article");
        article.setDescription("Super Article desc");
        article.setBody("test hoho");
        article.setTagList(Arrays.asList("test", "hoho"));
        return article;
    }


}
