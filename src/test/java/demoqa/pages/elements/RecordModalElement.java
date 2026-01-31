package demoqa.pages.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RecordModalElement {
    private final SelenideElement
            title = $("#registration-form-modal"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            age = $("#age"),
            salary = $("#salary"),
            department = $("#department"),
            submitButton = $("#submit");

    public void submitModalForm(String firstName,
                                String lastName,
                                String email,
                                int age,
                                int salary,
                                String department) {
        this
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setAge(age)
                .setSalary(salary)
                .setDepartment(department)
                .submit();
    }

    public RecordModalElement checkTitle(String expectedTitle) {
        title.shouldBe(visible)
                .shouldHave(text(expectedTitle));
        return this;
    }

    public RecordModalElement setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public RecordModalElement setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public RecordModalElement setEmail(String value) {
        email.setValue(value);
        return this;
    }

    public RecordModalElement setAge(int value) {
        age.setValue(String.valueOf(value));
        return this;
    }

    public RecordModalElement setSalary(int value) {
        salary.setValue(String.valueOf(value));
        return this;
    }

    public RecordModalElement setDepartment(String value) {
        department.setValue(value);
        return this;
    }

    public void submit() {
        submitButton.click();
    }
}
