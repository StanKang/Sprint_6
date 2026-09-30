package ru.yandex.samokat.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {

    protected WebDriver driver;

    protected void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    protected void setUp(String browser) {
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
            driver = new FirefoxDriver(options);
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
