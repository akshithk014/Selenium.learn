package com.domBlaze.TestBase;

import org.openqa.selenium.WebDriver;
import com.domBlaze.DriverFactory.WebdriverManager;
import com.domBlaze.Utility.EventListener;
import com.domBlaze.config.configReader;
import org.openqa.selenium.support.events.EventFiringDecorator;


public class TestBase extends configReader{
	
	public static WebDriver driver;
	 public static WebDriver e_driver;
	 
	 public TestBase() {
		 
	 }
		
	public static WebDriver intitialize(WebDriver webdriver,String url) {
		driver = webdriver; 
		EventListener eventlistner = new EventListener();;
		e_driver = new EventFiringDecorator(eventlistner).decorate(driver);
		e_driver.manage().window().maximize();
		e_driver.manage().deleteAllCookies();
		e_driver.get(url);
		driver = e_driver;
		return driver;
	}
	
}
