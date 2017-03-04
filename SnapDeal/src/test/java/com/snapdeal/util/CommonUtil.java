package com.snapdeal.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CommonUtil {

	
	public static String getPropertyValue(String fileName,String propertyName) throws IOException
	{
		FileInputStream fs=new FileInputStream(".\\Config\\"+fileName+".properties");
		Properties p=new Properties();
		
		p.load(fs);
		return p.getProperty(propertyName);
		
	}
}
