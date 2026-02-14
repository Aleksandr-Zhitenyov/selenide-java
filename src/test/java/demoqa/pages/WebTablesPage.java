package demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import demoqa.pages.elements.RecordModalElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTablesPage {
    private final RecordModalElement recordModalElement = new RecordModalElement();
    private final static String TITLE_PAGE = "Web Tables";
    private final static String TITLE_MODAL = "Registration Form";
    private final SelenideElement
            addNewRecordButton = $("#addNewRecordButton"),
            searchBoxInput = $("#searchBox");

    public WebTablesPage setRecordModal(String firstName,
                                        String lastName,
                                        String email,
                                        int age,
                                        int salary,
                                        String department) {
        recordModalElement
                .checkTitle(TITLE_MODAL)
                .submitModalForm(firstName, lastName, email, age, salary, department);
        return this;
    }

    public void searchRecordInTableByFirstName(String firstName) {
        searchBoxInput.setValue(firstName);
        $(".rt-tbody > .rt-tr-group .rt-td").shouldHave(text(firstName));
    }

    public WebTablesPage openPage() {
        open("/webtables");
        $("h1").shouldHave(text(TITLE_PAGE));
        return this;
    }

    public WebTablesPage addNewRecord() {
        addNewRecordButton.click();
        return this;
    }
}
