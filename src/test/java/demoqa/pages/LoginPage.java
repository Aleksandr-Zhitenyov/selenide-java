package demoqa.pages;

import demoqa.config.TestConfig;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class LoginPage {

    public void openPage() {
        open("/login");
    }

    public void login() {
        $("#userName").setValue(TestConfig.getLogin());
        $("#password").setValue(TestConfig.getPassword());
        $("#login").click();
    }

    public void verifyOpenProfilePage() {
        webdriver().shouldHave  (urlContaining("profile"));
    }
}
