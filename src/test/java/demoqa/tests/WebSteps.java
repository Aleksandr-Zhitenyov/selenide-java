package demoqa.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step
    public void openDemoqaMainPage() {
        open("https://demoqa.com/");
    }

    @Attachment(value = "Screenshot", type = "img/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        return driver.getPageSource().getBytes();
    }

    @Attachment(value = "Source", type = "text/plain", fileExtension = ".txt")
    public String attachPageSourceString() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }
}
