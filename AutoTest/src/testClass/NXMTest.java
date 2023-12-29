package testClass;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.NXMPageClass;

public class NXMTest {

	WebDriver driver;
	
	@BeforeTest
	public void setup()
		{
		driver=new ChromeDriver();
		
		}
	@BeforeMethod
	public void site()
	{
		driver.get("https://www.moddb.com");
		
	}

	@Test
	public void test() throws Exception
		{
		NXMPageClass r=new NXMPageClass(driver);
		driver.manage().window().maximize();
		r.login();
		r.upload();
		r.selector();
		r.savee();
		
		r.titlever();
		r.logover();
		r.contever();
		r.mousehvr();
		r.handlewindow();
		r.screenshots();
		
		
		
		
		
		;
		}

}
