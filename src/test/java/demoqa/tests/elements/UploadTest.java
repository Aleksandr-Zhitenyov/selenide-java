package demoqa.tests.elements;

import demoqa.tests.TestBase;
import demoqa.tests.WebSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class UploadTest extends TestBase {
    WebSteps webSteps = new WebSteps();

    @DisplayName("Should upload file on demoqa/upload-download")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("UPLOAD")
    @Test
    void shouldSuccessfulUploadFile() {
        step("Open page", () -> {
            open("/upload-download");
        });
        step("Upload file", () -> {
            $("h1").shouldHave(text("Upload and Download"));
            $("input[type='file']")
                    .shouldBe(visible)
                    .uploadFromClasspath("example/header_jmeter.jpg");
            attachment("Source", webdriver().driver().source());
            webSteps.takeScreenshot();
            webSteps.attachPageSource();
            webSteps.attachPageSourceString();
        });
        step("Verify result", () -> {
            $("#uploadedFilePath").shouldHave(text("header_jmeter.jpg"));
        });
    }
}
