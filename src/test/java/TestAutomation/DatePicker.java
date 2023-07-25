package TestAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.util.XlsReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker {

	public static WebDriver driver;
	static XlsReader reader = new XlsReader("excel path");
   
	@BeforeTest
	public static void openUrl() throws Exception {


		WebDriverManager.chromedriver().setup();ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://nxtgenaiacademy.com/demo-site/");
	}
	
	@Test
	public void dateTest() throws Exception
	{
		int rowCount = reader.getRowCount("Date");
		for(int i=2;i<=rowCount;i++) {
		driver.findElement(By.xpath("//*[@id=\"first_date_picker\"]")).click();
		String DateofMonth = reader.getCellData("Sheet1","DateofMonth",i);
		System.out.println(DateofMonth);
		String DateofYear = reader.getCellData("Sheet1","DateofYear",i);
		System.out.println(DateofYear);
		String date = reader.getCellData("Sheet1","Date", i);
		System.out.println(date);
		while(true)
		{
			String text = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String text1 = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

			if(text.equals(DateofMonth) && text1.equals(DateofYear))
			{
				driver.findElement(By.xpath("//*[@class=\"ui-datepicker-calendar\"]//tbody//tr//td//*[contains(text(),"+date+")]")).click();
			}
			else
			{
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			}
		}
//		driver.findElement(By.xpath("//*[@class=\"ui-datepicker-calendar\"]//tbody//tr//td//*[contains(text(),"+date+")]")).click();
 }
	}
}
