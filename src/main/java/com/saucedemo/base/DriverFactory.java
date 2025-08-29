package com.saucedemo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"))
                    || "true".equalsIgnoreCase(System.getenv("CI"));
            if (headless) {
                options.addArguments("--headless=new"); // headless mode
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            ChromeDriver driver = new ChromeDriver(options);
            if (!headless) {
                driver.manage().window().setSize(new Dimension(1920,1080));
            }
            DRIVER.set(driver);
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
