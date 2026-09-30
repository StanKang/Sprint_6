package ru.yandex.samokat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Кнопка «Заказать» вверху страницы
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");

    // Кнопка «Заказать» внизу страницы
    private final By bottomOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");

    // Логотип «Самокат» в хедере
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");

    // Логотип «Яндекс» в хедере
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // Заголовок вопроса в разделе «Вопросы о важном» по индексу (0-7)
    private final String faqItemByIndex = "//div[@id='accordion__heading-%d']";

    // Текст ответа на вопрос по индексу (0-7)
    private final String faqAnswerByIndex = "//div[@id='accordion__panel-%d']//p";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://qa-scooter.education-services.ru/");
    }

    public void clickScooterLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(scooterLogo)).click();
    }

    public void clickYandexLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(yandexLogo)).click();
    }

    public OrderPage clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
        return new OrderPage(driver);
    }

    public OrderPage clickBottomOrderButton() {
        WebElement button = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton)).click();
        return new OrderPage(driver);
    }

    public void clickFaqItem(int index) {
        By locator = By.xpath(String.format(faqItemByIndex, index));
        WebElement item = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public String getFaqQuestionText(int index) {
        By locator = By.xpath(String.format(faqItemByIndex, index));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String getFaqAnswerText(int index) {
        By locator = By.xpath(String.format(faqAnswerByIndex, index));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public boolean isFaqAnswerVisible(int index) {
        By locator = By.xpath(String.format(faqAnswerByIndex, index));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
