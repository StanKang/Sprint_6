package ru.yandex.samokat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Поле «Имя»
    private final By firstNameField = By.xpath("//input[@placeholder='* Имя']");

    // Поле «Фамилия»
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");

    // Поле «Адрес: куда привезти заказ»
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле «Станция метро»
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");

    // Выпадающий список станций метро — первый элемент
    private final By metroFirstOption = By.xpath("//ul[@class='select-search__options']/li[1]//button");

    // Поле «Телефон: на него позвонит курьер»
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка «Далее»
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Сообщение об ошибке под полем (имя, фамилия, адрес, телефон)
    private final By fieldErrorMessage = By.xpath("//div[contains(@class,'Input_ErrorMessage') and contains(@class,'Input_Visible')]");

    // Сообщение об ошибке под полем метро
    private final By metroErrorMessage = By.xpath("//*[contains(@class,'Order_UnderError') or contains(@class,'Order_MetroError')]");

    // Поле «Когда привезти самокат» (дата)
    private final By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Выпадающий список «Срок аренды»
    private final By rentalPeriodDropdown = By.xpath("//div[text()='* Срок аренды']");

    // Опция «двое суток» в выпадающем списке срока аренды
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");

    // Чекбокс цвета «чёрный жемчуг»
    private final By colorBlack = By.id("black");

    // Поле «Комментарий для курьера»
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка «Заказать»
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");

    // Кнопка «Да» в модальном окне подтверждения заказа
    private final By confirmButton = By.xpath("//button[text()='Да']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public OrderPage fillStep1(String firstName, String lastName, String address, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);

        driver.findElement(metroField).click();
        wait.until(ExpectedConditions.elementToBeClickable(metroFirstOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(phoneField)).sendKeys(phone);
        return this;
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public List<String> getFieldErrors() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldErrorMessage));
        List<String> errors = new ArrayList<>();
        for (WebElement el : driver.findElements(fieldErrorMessage)) {
            errors.add(el.getText());
        }
        return errors;
    }

    public String getMetroError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(metroErrorMessage)).getText();
    }

    public OrderPage fillStep2(String date, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDateField)).sendKeys(date);
        driver.findElement(deliveryDateField).sendKeys("\n");

        driver.findElement(rentalPeriodDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click();

        driver.findElement(colorBlack).click();
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    public OrderConfirmPage clickOrder() {
        driver.findElement(orderButton).click();
        WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirm.click();
        return new OrderConfirmPage(driver);
    }
}
