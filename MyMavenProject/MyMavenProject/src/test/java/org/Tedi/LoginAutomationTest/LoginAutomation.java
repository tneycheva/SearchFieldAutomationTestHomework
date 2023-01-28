package org.Tedi.LoginAutomationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginAutomation {
    @Test
    public void testLogin() {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();

        String expectedLoginPageUrl = "http://training.skillo-bg.com:4300/users/login";
        String actualLoginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Login page URL is incorrect!");

        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(signInTitle.isDisplayed(), "The sign in element is not displayed");

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("teddy");

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123456");

        WebElement signButton = driver.findElement(By.id("sign-in-button"));
        Assert.assertTrue(signButton.isEnabled());
        signButton.click();

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        profileLink.click();

        String expectedProfileUrl = "http://training.skillo-bg.com:4300/users/3927";
        String actualProfileUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualProfileUrl, expectedProfileUrl);

        String expectedUserName = "teddy";
        WebElement userName = driver.findElement(By.tagName("h2"));
        String actualUserName = userName.getText();
        Assert.assertEquals(actualUserName, expectedUserName, "The user name is incorrect");

        driver.close();



    }
}
