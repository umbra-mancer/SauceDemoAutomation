package com.saucedemo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory{
    private static WebDriver driver;

    public static WebDriver initDriver() {
        if(driver==null) {
            WebDriverManager.chromedriver().clearResolutionCache();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
    public static void quitDriver() {
        if(driver !=null) {
            driver.quit();
            driver=null;
        }
    }

}
