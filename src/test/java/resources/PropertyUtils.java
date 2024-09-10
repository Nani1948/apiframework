package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	static Properties pro=null;
	static FileInputStream fis=null;
	public static String getValueFromFile(String baseUrlKey) throws IOException {
	

			pro=new Properties();
			fis=new FileInputStream("C:\\Users\\knand\\eclipse-workspace\\APIFramework\\src\\test\\resources\\config.properties");
			pro.load(fis);
			return pro.getProperty(baseUrlKey);
			
		
		
	}

}
