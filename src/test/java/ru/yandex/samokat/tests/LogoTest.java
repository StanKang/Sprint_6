package ru.yandex.samokat.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.samokat.pageobjects.MainPage;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoTest extends BaseTest {

    @Test
    public void clickScooterLogoRedirectsToMainPage() {
        setUp("chrome");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickTopOrderButton();
        mainPage.clickScooterLogo();

        assertEquals("https://qa-scooter.education-services.ru/", driver.getCurrentUrl(),
                "После клика на логотип Самоката не произошёл переход на главную страницу");

        driver.quit();
    }

    @Test
    public void clickYandexLogoOpensYandexInNewTab() {
        setUp("chrome");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickYandexLogo();

        wait.until(driver -> driver.getWindowHandles().size() > 1);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(driver -> !driver.getCurrentUrl().equals("about:blank"));

        assertTrue(driver.getCurrentUrl().contains("yandex"),
                "После клика на логотип Яндекса не открылась страница Яндекса");

        driver.quit();
    }

    @AfterEach
    public void tearDownAfterEach() {
        tearDown();
    }
}
