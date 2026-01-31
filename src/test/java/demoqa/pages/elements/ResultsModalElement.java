package demoqa.pages.elements;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsModalElement {
    private static final String TITLE_TEXT = "Thanks for submitting the form";

    public void verifyModalAppears() {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text(TITLE_TEXT));
    }

    public void verifyKV(String key, String val) {
        $(".table-responsive")
                .$(byText(key))
                .parent()
                .shouldHave(text(val));
    }
}
