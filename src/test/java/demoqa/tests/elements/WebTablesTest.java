package demoqa.tests.elements;

import demoqa.pages.WebTablesPage;
import demoqa.tests.TestBase;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WebTablesTest extends TestBase {
    WebTablesPage webTablesPage = new WebTablesPage();

    @CsvSource({
            "John,Doe,john@example.com,25,5000,QA",
            "Jane,Smith,jane@example.com,30,6000,HR"
    })
    @DisplayName("Successful add new record and search in table by first name on demoqa/tables")
    @Severity(SeverityLevel.MINOR)
    @Tags({@Tag("UI_TEST"), @Tag("LOW")})
    @ParameterizedTest(name = "Should record and search by firstName: {0}")
    void successfulAddNewRecordAndSearchInTable(String firstName,
                                String lastName,
                                String email,
                                int age,
                                int salary,
                                   String department) {
        webTablesPage.openPage()
                .addNewRecord()
                .setRecordModal(firstName,lastName, email, age, salary, department)
                .searchRecordInTableByFirstName(firstName);
    }
}
