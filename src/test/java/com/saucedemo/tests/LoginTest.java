package com.saucedemo.tests;


import com.saucedemo.base.DriverFactory;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {

    WebDriver driver;
    private String username;
    private String password;
    @BeforeClass
    public void loadCredentials() {
        username = System.getenv("SAUCE_USERNAME");
        password = System.getenv("sauce_password");
        if (username == null || password == null) {
            Dotenv dotenv = Dotenv.load();
            username = dotenv.get("SAUCE_USERNAME");
            password = dotenv.get("SAUCE_PASSWORD");
        }
    }

    @BeforeMethod
    public void setup() {
        driver= DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testValidLogin() {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        assert driver.getCurrentUrl().contains("inventory");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}