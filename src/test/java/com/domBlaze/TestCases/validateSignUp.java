package com.domBlaze.TestCases;

import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.domBlaze.DriverFactory.WebdriverManager;
import com.domBlaze.TestBase.TestBase;

@Listeners(com.domBlaze.Listeners.ExtentListeners.class)
public class validateSignUp extends TestBase{

	private static WebDriver driver;

	@BeforeClass
	@Parameters({"browser","url"})
	public void setUp(String browser,String url) {
		driver = intitialize(WebdriverManager.getInstance(browser).getDriver(),url);
		PageFactory.initElements(driver, this);
	}
	
	@Test
	public void signUP() {
		Assert.assertEquals(driver.getTitle(), "STORE");
		WebElement signUp = driver.findElement(By.id("signin2"));
		Assert.assertEquals(signUp.getText(), "Sign up");
		signUp.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModalLabel")));
		 driver.findElement(By.id("sign-username")).sendKeys("abc@xyz.com");
		 driver.findElement(By.id("sign-password")).sendKeys("abc@xyz.com");
		 driver.findElement(By.xpath("//button[text()='Sign up']")).click();
		 wait.until(ExpectedConditions.alertIsPresent());
		 Assert.assertEquals(driver.switchTo().alert().getText(),"This user already exist.");
		 driver.switchTo().alert().accept();
	}

	
	@AfterClass
	public void tearDown() {
		WebdriverManager.quitBrowser();
	}

}
