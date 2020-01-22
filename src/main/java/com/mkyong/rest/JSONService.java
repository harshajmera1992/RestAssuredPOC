package com.mkyong.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.exec.environment.EnvironmentUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Path("/json/product")
public class JSONService {

	public static String PRODUCT_NAME = "";
	public static int PRODUCT_QTY ;
	//Product product1 = new Product();
	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getProductInJSON() {
		WebDriver driver = null;
		try{
			//ClassLoader classLoader = getClass().getClassLoader();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
			options.addArguments("window-size=1200x600");
			String binaryPath=EnvironmentUtils.getProcEnvironment().get("GOOGLE_CHROME_SHIM");
			System.out.println("Path: "+binaryPath);
			options.setBinary(binaryPath);     
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");       
			//System.out.println(JSONService.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//target//RESTfulExample//src//main//resources//driver//chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("-------------------"+System.getProperty("user.dir"));
		}
		catch(Exception ex){
			System.out.println("Ex1 :: "+ex);
			ex.printStackTrace();
		}
		try{
			InputStream input = new FileInputStream(JSONService.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"//excelData//testData//TestData.properties");
			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("URL"));
			
		} catch (IOException ex) { 
			System.out.println("Ex2 :: "+ex);
			ex.printStackTrace();
		}
		driver.manage().window().maximize(); 
		driver.navigate().to("http://www.facebook.com");
		System.out.println("Navigated to Facebook111111.com");
		Product product = new Product();
		product.setName(PRODUCT_NAME);
		product.setQty(PRODUCT_QTY);
		return product; 
	}

	@POST
	@Path("/post")
	@Consumes("application/json")
 	public Response createProductInJSON(Product product) {

		String result = "Product created : " + product.getName()+ product.getQty();
		//product1.setName(product.getName());
		//product1.setQty(product.getQty());
		PRODUCT_NAME = product.getName();
		PRODUCT_QTY = product.getQty();
		return Response.status(201).entity(result).build();
		
	}
	
}
