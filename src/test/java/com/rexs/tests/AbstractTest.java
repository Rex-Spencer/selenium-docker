package com.rexs.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    public void setDriver() {
        // Driver setup
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
