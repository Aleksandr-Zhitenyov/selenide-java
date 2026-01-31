package selenide.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @DisplayName("Parse file pdf from Junit docs")
    @Tags({@Tag("DOWNLOAD"), @Tag("PDF")})
    @Test
    void pdfParseTest() throws Exception {
        open("https://docs.junit.org/5.5.0/user-guide/");
        File downloadFilePdf = $("a[href='index.pdf']").download();
        PDF content = new PDF(downloadFilePdf);
        assertThat(content.author).contains("Stefan Bechtold");
    }

    @DisplayName("Parse file xls from resources")
    @Tag("XLS")
    @Test
    void xlsParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("example/Шаблон - план НТ.xlsx")) {
            XLS content = new XLS(resourceAsStream);
//           assertThat(content.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()).contains("1. Шаблон проекта НТ (первичное тестирование));
        }
    }

    @DisplayName("Parse file csv from resources")
    @Tag("SCV")
    @Test
    void csvParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("example/web_tables.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(resourceAsStream))) {
            List<String[]> content = reader.readAll();
            assertThat(content.getFirst()[1]).contains("Doe");
        }
    }

    @DisplayName("Parse file zip from resources")
    @Tag("ZIP")
    @Test
    void zipParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("example/test.zip");
             ZipInputStream zs = new ZipInputStream(resourceAsStream)) {
            if (zs.getNextEntry().getName().endsWith(".txt")) {
                String text = new String(zs.readAllBytes(), StandardCharsets.UTF_8);
                assertThat(text).contains("This file is in a zip archive.");
            }
        }
    }

    @DisplayName("Parse file json from resources")
    @Tag("JSON")
    @Test
    void jsonParseTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream resourceAsStream = cl.getResourceAsStream("example/person.json");
             InputStreamReader reader = new InputStreamReader(resourceAsStream)) {
            Person person = mapper.readValue(reader, Person.class);
            assertThat(person.getAddress().getCity()).contains("Wonderland");
        }
    }

    @JsonInclude(NON_NULL)
    static class Person {
        @JsonProperty("name")
        private String name;

        @JsonProperty("age")
        private int age;

        @JsonProperty("address")
        private Address address;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }
    }

    @JsonInclude(NON_NULL)
    static class Address {
        @JsonProperty("street")
        private String street;

        @JsonProperty("city")
        private String city;

        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
    }
}
