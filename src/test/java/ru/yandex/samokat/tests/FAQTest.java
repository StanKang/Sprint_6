package ru.yandex.samokat.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.yandex.samokat.pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FAQTest extends BaseTest {

    @ParameterizedTest(name = "Вопрос #{0}")
    @CsvSource({
            "0, Сколько стоит посуточная аренда самоката?",
            "1, Хочу сразу несколько самокатов! Так можно?",
            "2, Как рассчитывается время аренды?",
            "3, Можно ли заказать самокат прямо на сегодня?",
            "4, Можно ли продлить заказ или вернуть самокат раньше?",
            "5, Вы привозите зарядку вместе с самокатом?",
            "6, Можно ли отменить заказ?",
            "7, Я жду самокат, но он не приезжает. Что делать?"
    })
    public void faqItemOpensAnswer(int index, String expectedQuestion) {
        setUp("chrome");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFaqItem(index);
        assertTrue(mainPage.isFaqAnswerVisible(index),
                "Ответ на вопрос #" + index + " не отобразился");
        driver.quit();
    }

    @AfterEach
    public void tearDownAfterEach() {
        tearDown();
    }
}
