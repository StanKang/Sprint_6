package ru.yandex.samokat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmPage {

    private final WebDriverWait wait;

    // Модальное окно «Заказ оформлен»
    private final By confirmModal = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderConfirmPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSuccessModalVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmModal));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
