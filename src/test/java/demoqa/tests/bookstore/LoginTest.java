package demoqa.tests.bookstore;

import demoqa.pages.LoginPage;
import demoqa.tests.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class LoginTest extends TestBase {
    LoginPage loginPage = new LoginPage();

    @DisplayName("Should success login on demoqa.com")
    @Tags({@Tag("UI_TEST"), @Tag("BLOCKER"), @Tag("HIGH")})
    @Test
    void successfulLogin() {
        step("Open login page", () -> {
            loginPage.openPage();
        });
        step("Fill in the field with access data", () -> {
            loginPage.login();
        });
        step("Verify open profile page", () -> {
            loginPage.verifyOpenProfilePage();
        });
    }
}
