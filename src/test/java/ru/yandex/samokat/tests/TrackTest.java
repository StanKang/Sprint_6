package ru.yandex.samokat.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.yandex.samokat.pageobjects.TrackPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackTest extends BaseTest {

    @Test
    public void invalidOrderNumberShowsNotFound() {
        setUp();
        TrackPage trackPage = new TrackPage(driver);
        trackPage.open("99999999");
        trackPage.clickView();

        assertTrue(trackPage.isNotFoundImageVisible(),
                "Картинка 'Not found' не отобразилась для несуществующего заказа");

        driver.quit();
    }

    @AfterEach
    public void tearDownAfterEach() {
        tearDown();
    }
}
