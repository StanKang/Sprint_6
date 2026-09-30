package ru.yandex.samokat.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;

    protected void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
