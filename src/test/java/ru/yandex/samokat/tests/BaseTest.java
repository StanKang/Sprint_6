package ru.yandex.samokat.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class BaseTest {

    protected WebDriver driver;

    protected void setUp(String browser) {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/opt/homebrew/bin/chromedriver"))
                .build();
        driver = new ChromeDriver(service);
        driver.manage().window().maximize();
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
