package selenide.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import selenide.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedTest {
    static Stream<Arguments> dataProvider() {
        return Stream.of(Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")),
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "For locale {0} view buttons {1}")
    @Tag("SELENIDE_UI")
    void selenideSiteShouldContainCurrentNameButtonForGivenLocale(
            Locale locale,
            List<String> buttons
    ) {
        open("https://ru.selenide.org/");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a")
                .filter(visible)
                .shouldHave(texts(buttons));
    }
}
