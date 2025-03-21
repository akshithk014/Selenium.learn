package com.domBlaze.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverManager {
	
	private static volatile WebdriverManager instance;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	private WebdriverManager() {
	}
	
	private void initDriver(String browser) {
//		System.out.println(browser);

		if(browser.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver());
		}else if(browser.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver());
		}else {
			throw new IllegalArgumentException("Check browser name");
		}
	}

	public static WebdriverManager getInstance(String browser) {
		if(instance == null) {
			synchronized (browser) {
				if(instance == null) {
					instance = new WebdriverManager();
				}
			}
		}
		if(tlDriver.get() == null) {
			instance.initDriver(browser);
		}
		return instance;
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public static void quitBrowser() {
		if(tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}
	

}
