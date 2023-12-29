package page;



	

import java.awt.AWTException; 
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
	


	public class NXMPageClass {	

		WebDriver driver;
		By login=By.xpath("//*[@id=\"togglelogin\"]");
		By user=By.xpath("//input[@placeholder='Username']");
		By pass=By.name("password");
		By loginbtn=By.xpath("//*[@id=\"searchbox\"]/form[2]/input[5]");
		By logo=By.xpath("//*[@id=\"header\"]/a");
		By games=By.xpath("//*[@id=\"subheader\"]/nav/ul/li[1]/a"); 
		By pop=By.xpath("//*[@id=\"subheader\"]/nav/ul/li[1]/ul/li[1]/a");
		By tou=By.xpath("//*[@id=\"footer\"]/div/span[1]/a[2]");
		By fac=By.xpath("//*[@id=\"body\"]/div/div[3]/div[2]/div/div/div[2]/a[5]");
		By prof=By.xpath("//*[@id=\"searchbox\"]/span/a[1]");
		By editprof=By.xpath("//*[@id=\"membersinfo\"]/div[2]/div/div[2]/div[1]/span/a");
		By upld=By.xpath("//*[@id=\"membersavatar\"]");
		By logut=By.xpath("//*[@id=\"searchbox\"]/span/a[5]");
		By save=By.xpath("//*[@id=\"memberssubmit\"]");
		
		public NXMPageClass(WebDriver driver) 
		
		{	
			this.driver=driver;
			
		}
		
	//title verifcation
		public void titlever() 
		
		{
			String act=driver.getTitle();
			System.out.println(act);
			String exp="Games and mods development for Windows, Linux and Mac - Mod DB";
			if(act.equals(exp)) 
			{
				System.out.println("same title");
				
			}
			else 
			{
				
				System.out.println("diff title");
			}
		}
			//content ver
			public void	contever() 
			{
				String cont=driver.getPageSource();
				if(cont.contains("moddb")) 
				{
					System.out.println("contains");
					
				}
				else 
					{
					
					System.out.println("not contains");
					}
			}
			
			//logo verif//
			public void logover() 
			{
				WebElement lo=driver.findElement(logo);
				boolean b=lo.isDisplayed();
				if(b) {
					
					System.out.println("logo is displayed");
				}
				else 
				{
					System.out.println("logo not displayed");
					
				}
			}
			
			//screenshot
			public void screenshots() throws Exception
			{
				TakesScreenshot ts = (TakesScreenshot) driver;
				File f = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(f,new File("C:\\\\screenshot\\\\ab.png"));
				//File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//FileHandler.copy(f,new File("C:\\\\screenshot\\\\ab.png"));
			}
		//mousehover
			public void mousehvr() 
			{
				Actions act=new Actions(driver);
				WebElement ai=driver.findElement(games);
				act.moveToElement(ai);
				act.perform();
				driver.findElement(pop).click();
				driver.navigate().back();
				
			}

	public void handlewindow() 
		{
		driver.findElement(By.xpath("//*[@id=\"footer\"]/div/span[2]/a[4]")).click();
		String parentwindow=driver.getWindowHandle();
		driver.findElement(fac).click();
		
		Set<String> child=driver.getWindowHandles();
		for(String hd:child)
		{
		if(!hd.equalsIgnoreCase(parentwindow)) 
		{
			driver.switchTo().window(hd);
			
			
		
			driver.close();
			
		}
		driver.switchTo().window(parentwindow);
		}
			
		driver.navigate().back();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)","");
		}

	public void scroll() 
		{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)","");
		
		}

	public void login() throws IOException

	{
		driver.findElement(login).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		File f=new File("C:\\Users\\ASAS\\Documents\\Book1.xlsx");
		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("sheet1");
		System.out.println(sh.getLastRowNum());
		WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));

		for(int i=1;i<sh.getLastRowNum();i++) 
			{
			
			
			String username=sh.getRow(i).getCell(0).getStringCellValue();
			
			String passw=sh.getRow(i).getCell(1).getStringCellValue();
			
			w.until(ExpectedConditions.visibilityOfElementLocated(user));
			w.until(ExpectedConditions.visibilityOfElementLocated(pass));
				
			driver.findElement(user).sendKeys(username);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.findElement(pass).sendKeys(passw);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.findElement(loginbtn).click();
			
			}
	}
	public void upload() throws Exception
		{
		
		driver.findElement(prof).click();
		driver.findElement(editprof).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Actions ac=new Actions(driver);
		//driver.findElement(upld).click();
		ac.moveToElement(driver.findElement(upld)).click().build().perform();
		fileupload("C:\\Users\\ASAS\\Pictures\\Saved Pictures\\600-01717982en_Masterfile.jpg");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
		
		}
	private void fileupload(String m) throws AWTException
		{
		
		StringSelection strsel=new StringSelection(m);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strsel, null);
		
		Robot rob=new Robot();
		rob.delay(3000);
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		}
	public void selector() 
		{
		
	WebElement d=driver.findElement(By.xpath("//*[@id=\"membersday\"]"));
	 	
		WebElement y=driver.findElement(By.xpath("//*[@id=\"membersyear\"]"));
		Select year=new Select(y);
		year.selectByVisibleText("2002");

		WebElement m=	driver.findElement(By.xpath("//*[@id=\"membersmonth\"]"));
		Select month=new Select(m);
		month.selectByIndex(11);
		
		Select day=new Select(d);
		day.selectByValue("21");
		}


		

	public void savee() 
		{
		driver.findElement(save).click();
		driver.findElement(By.xpath("//*[@id=\"searchbox\"]/span/a[5]")).click();

		}
	}
