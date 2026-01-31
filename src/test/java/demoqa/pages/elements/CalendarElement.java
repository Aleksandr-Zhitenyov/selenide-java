package demoqa.pages.elements;

import static com.codeborne.selenide.Selenide.$;

public class CalendarElement {
    public void setDate( Integer day, String month, String year) {
        $("[class*='month-select']").selectOption(month);
        $("[class*='year-select']").selectOption(year);
        String dayFormatted = String.format("%03d", day);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();
    }
}
