package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static final WebSteps webSteps = new WebSteps();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1500x900";

        SelenideLogger.addListener("allure", new AllureSelenide());

        webSteps.openDemoqaMainPage();
    }
}
