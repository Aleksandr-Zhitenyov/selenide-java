package demoqa.tests.forms;

import demoqa.pages.RegistrationFormPage;
import demoqa.tests.TestBase;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;


public class RegistrationFormTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(); // Emory
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    List<String> genders = Arrays.asList("Male", "Female", "Other");
    String gender = faker.options().option(genders).getLast();
    String phoneNumber = faker.phoneNumber().subscriberNumber(10);
    LocalDate birthDate = faker.timeAndDate().birthday(10, 25);
    int day = birthDate.getDayOfMonth();
    String month = birthDate.getMonth().name().charAt(0)
            + birthDate.getMonth().name().substring(1).toLowerCase();
    String year = String.valueOf(birthDate.getYear());
    List<String> subjects = Arrays.asList(
            "Maths", "Physics", "Chemistry",
            "Biology", "Computer Science", "English"
    );
    String subject = faker.options().option(subjects).getLast();
    String currentAddress = faker.address().fullAddress();

    @DisplayName("Successful submit registration form on demoqa/forms")
    @Tags({@Tag("UI_TEST"), @Tag("CRITICAL")})
    @Test
    void successfulRegistrationTest() {
        step("Open registration form page", () -> {
            registrationFormPage.openPage();
        });
        step("Filling out the form", () -> {
            registrationFormPage.setFirstName(firstName);
            registrationFormPage.setLastName(lastName);
            registrationFormPage.setEmail(email);
            registrationFormPage.setGender(gender);
            registrationFormPage.setUserNumber(phoneNumber);
            registrationFormPage.setBirthDate(day, month, year);
            registrationFormPage.setSubject(subject);
            registrationFormPage.setHobbies("Sports");
            registrationFormPage.setCurrentAddress(currentAddress);
            registrationFormPage.uploadPicture("example/header_jmeter.jpg");
            registrationFormPage.selectState("NCR");
            registrationFormPage.selectCity("Gurgaon");
            registrationFormPage.submitForm();
        });
        step("Verify result", () -> {
            registrationFormPage.resultsModal()
                    .verify("Student Name", String.format("%s %s", firstName, lastName))
                    .verify("Student Email", email)
                    .verify("Gender", gender)
                    .verify("Mobile", phoneNumber)
                    .verify("Date of Birth", String.format("%d %s,%s", day, month, year))
                    .verify("Subjects", subject)
                    .verify("Hobbies", "Sports")
                    .verify("Picture", "header_jmeter.jpg")
                    .verify("State and City", "NCR Gurgaon")
                    .verify("Address", currentAddress);
        });
    }
}
