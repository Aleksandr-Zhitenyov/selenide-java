package demoqa.tests.elements;

import demoqa.tests.TestBase;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest extends TestBase {

    @DisplayName("Successful submit form on demoqa/text-box")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("UI_TEST")})
    @Test
    void shouldSubmitForm() {
        open("/text-box");

        $x("//h1").should(visible);
        $x("//*[@id='userName']").setValue("userNameText");
        $x("//*[@id='userEmail']").setValue("name@example.com");
        $x("//*[@id='currentAddress']").setValue("Moscow city");
        $x("//*[@id='permanentAddress']").setValue("St. Petersburg city");
        $x("//*[@id='submit']").click();

        $x("//*[@id='output']//*[@id='name']").shouldHave(text("usernametext")); // Contains case-insensitive text
        $x("//*[@id='output']").find("#email").shouldHave(exactText("email:name@example.com")); // Case-insensitive exact match
        $x("//*[@id='output']").$x(".//*[@id='currentAddress']").shouldHave(textCaseSensitive("Moscow city")); // Contains case-sensitive text
        $("#output").$x(".//*[@id='permanentAddress']").shouldHave(exactTextCaseSensitive("Permananet Address :St. Petersburg city")); // Case-sensitive exact match
    }
}
