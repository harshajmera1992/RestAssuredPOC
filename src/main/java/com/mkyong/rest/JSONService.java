package com.mkyong.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
			System.setProperty("webdriver.chrome.driver", ("/src/main/resources/driver/chromedriver.exe"));
			driver = new ChromeDriver();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		driver.manage().window().maximize(); 
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
