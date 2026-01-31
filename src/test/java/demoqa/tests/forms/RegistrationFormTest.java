package demoqa.tests.forms;

import demoqa.pages.RegistrationFormPage;
import demoqa.tests.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class RegistrationFormTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @DisplayName("Successful submit registration form on demoqa/forms")
    @Tags({@Tag("UI_TEST"), @Tag("CRITICAL")})
    @Test
    void successfulRegistrationTest() {
        registrationFormPage.openPage();

        registrationFormPage.setFirstName("First Name");
        registrationFormPage.setLastName("Last Name");
        registrationFormPage.setEmail("user_name@example.com");
        registrationFormPage.setGender("Male");
        registrationFormPage.setUserNumber("1234567890");
        registrationFormPage.setBirthDate(5, "March", "2015");
        registrationFormPage.setSubject("Maths");
        registrationFormPage.setHobbies("Sports");
        registrationFormPage.setCurrentAddress("Moscow city");
        registrationFormPage.submitForm();

        registrationFormPage.resultsModal()
                .verify("Student Name", "First Name " + "Last Name")
                .verify("Student Email", "user_name@example.com")
                .verify("Gender", "Male")
                .verify("Mobile", "1234567890")
                .verify("Date of Birth", "5 March,2015")
                .verify("Subjects", "Maths")
                .verify("Hobbies", "Sports")
                .verify("Address", "Moscow city");
    }
}
