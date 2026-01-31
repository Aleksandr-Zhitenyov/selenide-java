package demoqa.tests.elements;

import demoqa.tests.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UploadTest extends TestBase {

    @DisplayName("Should upload file on demoqa/upload-download")
    @Tag("UPLOAD")
    @Test
    void shouldSuccessfulUploadFile() {
        open("/upload-download");
        $("h1").shouldHave(text("Upload and Download"));
        $("input[type='file']")
                .shouldBe(visible)
                .uploadFromClasspath("example/header_jmeter.jpg");
        $("#uploadedFilePath").shouldHave(text("header_jmeter.jpg"));
    }
}
