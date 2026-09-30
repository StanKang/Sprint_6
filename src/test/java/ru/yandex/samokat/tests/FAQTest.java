package ru.yandex.samokat.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.samokat.pageobjects.MainPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FAQTest extends BaseTest {

    static Stream<Object[]> faqData() {
        return Stream.of(
            new Object[]{0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            new Object[]{1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            new Object[]{2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            new Object[]{3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            new Object[]{4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            new Object[]{5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            new Object[]{6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            new Object[]{7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        );
    }

    @ParameterizedTest(name = "Вопрос #{0}")
    @MethodSource("faqData")
    public void faqItemOpensAnswer(int index, String expectedQuestion, String expectedAnswer) {
        setUp();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFaqItem(index);
        assertEquals(expectedQuestion, mainPage.getFaqQuestionText(index),
                "Текст вопроса #" + index + " не совпадает");
        assertEquals(expectedAnswer, mainPage.getFaqAnswerText(index),
                "Текст ответа #" + index + " не совпадает");
        driver.quit();
    }

    @AfterEach
    public void tearDownAfterEach() {
        tearDown();
    }
}
