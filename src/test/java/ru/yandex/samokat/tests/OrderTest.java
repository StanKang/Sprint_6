package ru.yandex.samokat.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.samokat.pageobjects.MainPage;
import ru.yandex.samokat.pageobjects.OrderConfirmPage;
import ru.yandex.samokat.pageobjects.OrderPage;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.regex.Pattern;

public class OrderTest extends BaseTest {

    static Stream<Object[]> orderData() {
        return Stream.of(
                new Object[]{"top", "Иван", "Иванов", "ул. Ленина 1", "+79161234567", "25.12.2024", "Позвонить за час"},
                new Object[]{"bottom", "Мария", "Петрова", "пр. Мира 42", "+79261234567", "26.12.2024", "Домофон 15"}
        );
    }

    @ParameterizedTest(name = "Кнопка: {0}, имя: {1}")
    @MethodSource("orderData")
    public void orderScooterSuccessfully(
            String entryPoint,
            String firstName,
            String lastName,
            String address,
            String phone,
            String date,
            String comment
    ) {
        setUp();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        OrderPage orderPage;
        if (entryPoint.equals("top")) {
            orderPage = mainPage.clickTopOrderButton();
        } else {
            orderPage = mainPage.clickBottomOrderButton();
        }

        orderPage.fillStep1(firstName, lastName, address, phone);
        orderPage.clickNext();
        OrderConfirmPage confirmPage = orderPage.fillStep2(date, comment).clickOrder();

        assertTrue(confirmPage.isSuccessModalVisible(),
                "Модальное окно не появилось");

        driver.quit();
    }

    @Test
    public void orderConfirmedWithOrderNumber() {
        setUp();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        OrderPage orderPage = mainPage.clickTopOrderButton();
        orderPage.fillStep1("Иван", "Иванов", "ул. Ленина 1", "+79161234567");
        orderPage.clickNext();
        OrderConfirmPage confirmPage = orderPage.fillStep2("25.12.2024", "Позвонить за час").clickOrder();
        assertTrue(confirmPage.isSuccessModalVisible(), "Модальное окно не появилось");
        String text = confirmPage.getOrderNumberText();
        assertTrue(Pattern.compile("Номер заказа: \\d+").matcher(text).find(),
                "Номер заказа не найден в тексте: " + text);
        driver.quit();
    }

    @Test
    public void emptyFieldsShowErrors() {
        setUp();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        OrderPage orderPage = mainPage.clickTopOrderButton();
        orderPage.clickNext();

        List<String> errors = orderPage.getFieldErrors();
        assertTrue(errors.contains("Введите корректное имя"), "Нет ошибки для поля Имя");
        assertTrue(errors.contains("Введите корректную фамилию"), "Нет ошибки для поля Фамилия");
        assertTrue(errors.contains("Введите корректный номер"), "Нет ошибки для поля Телефон");
        assertTrue(orderPage.getMetroError().contains("Выберите станцию"), "Нет ошибки для поля Метро");

        driver.quit();
    }

    @AfterEach
    public void tearDownAfterEach() {
        tearDown();
    }
}
