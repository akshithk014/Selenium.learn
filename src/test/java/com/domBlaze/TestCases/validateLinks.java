package com.domBlaze.TestCases;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.domBlaze.DriverFactory.WebdriverManager;
import com.domBlaze.TestBase.TestBase;

@Listeners(com.domBlaze.Listeners.ExtentListeners.class)
public class validateLinks  extends TestBase{
	
	//Page Factory
	@FindBy(xpath = "//h5[text()='Elements']/ancestor::div[contains(@class,'top-card')]")
	WebElement ElementsCard;
	@FindBy(xpath = "//div[text()='Elements']/ancestor::div[@class='element-group']/div")
	WebElement ElementAccordion;
	@FindBy(xpath = "//span[text()='Broken Links - Images']/parent::li")
	WebElement Links;

	private static WebDriver driver;
	

	@BeforeClass
	@Parameters({"browser","url"})
	public void setUp(String browser,String url) {
		driver = intitialize(WebdriverManager.getInstance(browser).getDriver(),url);
		PageFactory.initElements(driver, this);
	}
	
	@Test
	public void verifyBrokenLinks() {
		Assert.assertEquals(driver.getTitle(), "DEMOQA");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ElementsCard)).click();
		wait.until(ExpectedConditions.visibilityOf(ElementAccordion));
		Assert.assertTrue(ElementAccordion.getDomAttribute("class").contains("show"), "Elemets Accordion is not expanded");
		Links.click();	
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement link: links) {
			verifyUrl(link.getDomAttribute("href"));
		}
	}
	
	public static void verifyUrl(String urlLink) {
		try {
			URL url = new URL(urlLink);
			HttpURLConnection httpURLConnection =  (HttpURLConnection)url.openConnection();
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.connect();
			if(httpURLConnection.getResponseCode()>400) {
				System.out.println(urlLink+"-"+httpURLConnection.getResponseMessage()+" is a broken link");
			}else {
				System.out.println(urlLink+"-"+httpURLConnection.getResponseMessage()+" is a vaild link");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	
	@AfterClass
	public void tearDown() {
		WebdriverManager.quitBrowser();
	}

}