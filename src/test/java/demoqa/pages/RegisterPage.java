package demoqa.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegisterPage {
    private final static String TITLE_TEXT = "Register";
    private final SelenideElement
            firstnameInput = $("#firstname"),
            lastnameInput = $("#lastname"),
            userNameInput = $("#userName"),
            passwordInput = $("#password"),
            registerButton = $("#register"),
            newUserButton = $("#newUser");

    public RegisterPage openPage() {
        new LoginPage().openPage();
        executeJavaScript("$('[id*=Ad]').remove()");
        newUserButton.click();
        webdriver().shouldHave(url(Configuration.baseUrl + "/register"));
        $("h1").shouldHave(text(TITLE_TEXT));
        return this;
    }

    public RegisterPage setFirstname(String val) {
        firstnameInput.setValue(val);
        return this;
    }

    public RegisterPage setLastname(String val) {
        lastnameInput.setValue(val);
        return this;
    }

    public RegisterPage setUserName(String val) {
        userNameInput.setValue(val);
        return this;
    }

    public RegisterPage setPassword(String val) {
        passwordInput.setValue(val);
        return this;
    }

    public RegisterPage checkCaptcha() {
        switchTo().frame($("iframe[title='reCAPTCHA']"));
        $("#recaptcha-anchor")
                .shouldBe(visible).click();
        $("#recaptcha-anchor")
                .shouldHave(attribute("aria-checked", "true"), Duration.ofSeconds(5));
        switchTo().defaultContent();
        return this;
    }

    public void clickRegister() {
        registerButton.click();
        Selenide.confirm();
    }
}
