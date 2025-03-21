package com.domBlaze.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configReader {

public static Properties configProp;

public configReader() {
	configProp = new Properties();
	try {
		FileInputStream config = new FileInputStream("C:\\Users\\U6074902\\eclipse-workspace\\learn.selenium\\src\\main\\java\\com\\domBlaze\\config\\config.properties");
		configProp.load(config);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	} 
}

}
