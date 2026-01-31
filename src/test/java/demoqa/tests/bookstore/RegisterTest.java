package demoqa.tests.bookstore;

import demoqa.pages.RegisterPage;
import demoqa.tests.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class RegisterTest extends TestBase {
    RegisterPage registerPage = new RegisterPage();

    @DisplayName("Registration on demoqa.com")
    @Tags({@Tag("UI_TEST"), @Tag("BLOCKER"), @Tag("HIGH")})
    @Test
    void successfulRegister() {
        registerPage.openPage()
                .setFirstname("name3")
                .setLastname("last3")
                .setUserName("user3")
                .setPassword("Aa1234Zz!")
                .checkCaptcha()
                .clickRegister();
    }
}
