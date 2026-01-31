package demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import demoqa.pages.elements.CalendarElement;
import demoqa.pages.elements.ResultsModalElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    private final CalendarElement calendarElement = new CalendarElement();
    private final ResultsModalElement resultsModalElement = new ResultsModalElement();
    private final static String TITLE_TEXT = "Practice Form";
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadiobutton = $("#genterWrapper"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            numberInput = $("#userNumber"),
            subjectInput = $("input#subjectsInput"),
            hobbiesCheckBox = $("#hobbiesWrapper"),
            currentAddressInput = $("#currentAddress"),
            submitButton = $("#submit");

    public void openPage() {
        open("/automation-practice-form");
        $("h1").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#RightSide_Advertisement').closest('[class*=col]').remove()");
    }

    public void setFirstName(String val) {
        firstNameInput.setValue(val);
    }

    public void setLastName(String val) {
        lastNameInput.setValue(val);
    }

    public void setEmail(String val) {
        emailInput.setValue(val);
    }

    public void setGender(String val) {
        genderRadiobutton.$(byText(val)).click();
    }

    public void setUserNumber(String val) {
        numberInput.setValue(val);
    }

    public void setBirthDate(Integer day, String month, String year) {
        dateOfBirthInput.click();
        calendarElement.setDate(day, month, year);
    }

    public void setSubject(String val) {
        subjectInput.setValue(val).pressEnter();
    }

    public void setHobbies(String val) {
        hobbiesCheckBox.find(byText(val)).click();
    }

    public void setCurrentAddress(String val) {
        currentAddressInput.setValue(val);
    }

    public void submitForm() {
        submitButton.click();
    }

    public RegistrationFormPage resultsModal() {
        resultsModalElement.verifyModalAppears();
        return this;
    }

    public RegistrationFormPage verify(String key, String val) {
        resultsModalElement.verifyKV(key, val);
        return this;
    }
}
