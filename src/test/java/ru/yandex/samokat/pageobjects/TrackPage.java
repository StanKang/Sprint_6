package ru.yandex.samokat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Кнопка «Посмотреть» на странице статуса заказа
    private final By viewButton = By.xpath("//button[text()='Посмотреть']");

    // Картинка «Not found» — заказ не найден
    private final By notFoundImage = By.xpath("//img[@alt='Not found']");

    public TrackPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String orderNumber) {
        driver.get("https://qa-scooter.education-services.ru/track?t=" + orderNumber);
    }

    public void clickView() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(viewButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public boolean isNotFoundImageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
