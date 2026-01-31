package selenide.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DownloadTest {

    @DisplayName("Should successful download README.md file from github")
    @Tag("DOWNLOAD")
    @Test
    void shouldDownloadReadmeFileFromGithub() throws Exception {
        open("https://github.com/selenide/selenide/blob/main/README.md");
        File downloadFile = $("[data-testid='raw-button']").download();
        try (InputStream is = new FileInputStream(downloadFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
        }
    }
}
