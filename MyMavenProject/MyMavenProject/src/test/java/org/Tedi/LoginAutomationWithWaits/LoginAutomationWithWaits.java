package org.Tedi.LoginAutomationWithWaits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.asn1.x509.DisplayText;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginAutomationWithWaits {
    @Test
    public void testLogin() {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

  /*      String expectedLoginPageUrl = "http://training.skillo-bg.com:4300/users/login";
        String actualLoginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Login page URL is incorrect!"); */


        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));


        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("teddy");

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123456");

        WebElement signButton = driver.findElement(By.id("sign-in-button"));
        wait.until(ExpectedConditions.elementToBeClickable(signButton));
        signButton.click();

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();


        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));


 /*       WebElement userName = driver.findElement(By.tagName("h2"));
        String actualUserName = userName.getText();
        String expectedUserName = "teddy";
        Assert.assertEquals(actualUserName, expectedUserName, "The user name is incorrect"); */

       Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "teddy"));
       Assert.assertTrue(isTextDisplayed, "The username is not displayed");

        driver.close();
    }

    @Test
    public void testRegistration() {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));

        WebElement signUptitle = driver.findElement(By.xpath("//*[text()='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(signUptitle));

        WebElement username = driver.findElement(By.name("username"));
        username.clear();
        username.sendKeys("tedi94");

        WebElement email = driver.findElement(By.xpath("//*[@type='email']"));
        email.sendKeys("tedi94@test.com");

        WebElement birthDate = driver.findElement(By.xpath("//*[@formcontrolname='birthDate'] "));
        birthDate.sendKeys("02021994");
    }
}
