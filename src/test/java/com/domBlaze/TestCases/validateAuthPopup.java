package com.domBlaze.TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import com.domBlaze.DriverFactory.WebdriverManager;
import com.domBlaze.TestBase.TestBase;

@Listeners(com.domBlaze.Listeners.ExtentListeners.class)
public class validateAuthPopup extends TestBase{

	private static WebDriver driver;

	@BeforeClass
	@Parameters({"browser","url"})
	public void setUp(String browser,String url) {
		driver = intitialize(WebdriverManager.getInstance(browser).getDriver(),url);
		PageFactory.initElements(driver, this);
	}
	
	@Test
	public void authPopup() throws AWTException {
		String[] url = driver.getCurrentUrl().split("//");
		System.out.println(url[0]+"//admin:admin@"+url[1]);
		driver.findElement(By.linkText("Basic Auth")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.alertIsPresent());
		Robot robot = new Robot();
		String username = "admin", password = "admin";
		robot.delay(2000);
		for(char c: username.toCharArray()) {
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
			robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
		}
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(500);
		for(char c: password.toCharArray()) {
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
			robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
		}
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		//Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait.until(ExpectedConditions.urlContains("basic_auth"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//body")),"Congratulations! You must have the proper credentials."));
	}
	
	
//	@AfterClass
	public void tearDown() {
		WebdriverManager.quitBrowser();
	}
}
