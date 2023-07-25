package TestAutomation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

	static WebDriver driver;

	@BeforeTest
	public static void openUrl() throws Exception {

		WebDriverManager.chromedriver().setup();ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://nxtgenaiacademy.com/demo-site/");
	}

	@DataProvider
	public Iterator<Object[]> getTestData() throws Exception {
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel();
		return testData.iterator();
	}

	@Test(dataProvider="getTestData")
	public static void Details(String Fn,String Ln,String Add,String Email,String Ph,
			String gender,String hobby,String hobby1,String hobby2,String Lang,String Skills,String Country,String BirthYear,
			String BirthMonth,String BirthDay,String Pswd,String ConfPswd) throws Exception {

		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(Fn);
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(Ln);
		driver.findElement(By.xpath("//*[@ng-model='Adress']")).sendKeys(Add);
		driver.findElement(By.xpath("//*[@ng-model='EmailAdress']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@ng-model='Phone']")).sendKeys(Ph);
		if(gender.equalsIgnoreCase("Male")) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}else {
			driver.findElement(By.xpath("//input[@value='FeMale']")).click();
		}
		Thread.sleep(1000);
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for(WebElement chkbox : checkboxes) {
			String checkboxvalue = chkbox.getAttribute("value");
			if(checkboxvalue.equals(hobby)||checkboxvalue.equals(hobby1)||checkboxvalue.equals(hobby2)) {
				chkbox.click();
			}
		}
		Thread.sleep(1000);
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='msdd']")).click();
		driver.findElement(By.xpath("//*[text()='" + Lang + "']")).click();
		Thread.sleep(1000);
		WebElement drp = driver.findElement(By.xpath("//*[@id='Skills']"));
		Select se1 = new Select(drp);
		se1.selectByVisibleText(Skills);
		driver.findElement(By.xpath("//*[@role='combobox']")).click();
		driver.findElement(By.xpath("//*[@role='textbox']")).sendKeys(Country);
		driver.findElement(By.xpath("//*[@role='treeitem']")).click();
		WebElement Byear = driver.findElement(By.id("yearbox"));
		Select se2 = new Select(Byear);
		se2.selectByVisibleText(BirthYear);
		WebElement BMonth = driver.findElement(By.xpath("//*[@placeholder='Month']"));
		Select se3 = new Select(BMonth);
		se3.selectByVisibleText(BirthMonth);
		WebElement BDay = driver.findElement(By.id("daybox"));
		Select se4 = new Select(BDay);
		se4.selectByVisibleText(BirthDay);
		driver.findElement(By.xpath("//*[@id='firstpassword']")).sendKeys(Pswd);
		driver.findElement(By.xpath("//*[@id='secondpassword']")).sendKeys(ConfPswd);

		driver.findElement(By.xpath("//*[@id='Button1']")).click();

	}
}