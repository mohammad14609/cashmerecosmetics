import java.awt.Window;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class registration_process {

	public WebDriver driver;
	
	SoftAssert checking_process = new SoftAssert();
	String myEmail = "mo_testing_1@hotmail.com";
	String myPaaword = "Testtest@123";
	String myPhoneNum = "0776801890";
	String myName ="mohammad ismael";


	@BeforeTest(groups = "log")
	public void open_main_page() throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://www.cashmerecosmetics.com");// main web page
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"col-952604097\"]/div/a[1]")).click();// popup msg to chose your
																							// language

	}

	@Test(groups = "reg")

	public void Try_to_submit_new_account() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//*[@id=\"menu-item-138\"]/a")).click(); // click on my account link.
		driver.findElement(By.id("reg_billing_phone")).sendKeys(myPhoneNum);
		driver.findElement(By.id("reg_billing_first_name")).sendKeys("mohammad ismael");
		driver.findElement(By.id("reg_billing_last_name")).sendKeys("othman");
		driver.findElement(By.id("reg_billing_loyalty_number")).sendKeys("241991");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,950)");
		driver.findElement(By.name("email")).sendKeys(myEmail);
		Thread.sleep(1000);
		driver.findElement(By.name("register")).click();
		
//		String actul_social_login = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/div/h1")).getText(); 
//		checking_process.assertEquals(actul_social_login,"my");
//		checking_process.assertAll();
		
	}
	@Test(groups = "log")
	
	public void login_process () throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"menu-item-138\"]/a")).click(); // click on my account link.
		driver.findElement(By.id("username")).sendKeys(myName);
		driver.findElement(By.id("password")).sendKeys(myPaaword);
		driver.findElement(By.name("login")).click();
	String errorPopuMsg = "If you are unsure of your username, try your email address instead.";
		String errorMsg =driver.findElement(By.xpath("//*[@id=\"wrapper\"]/ul/li/div")).getText(); 
		if(errorMsg.contains(errorPopuMsg)) {
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.findElement(By.name("username")).clear();
			driver.findElement(By.id("username")).sendKeys(myEmail);
			driver.findElement(By.id("password")).sendKeys(myPaaword);
			driver.findElement(By.name("login")).click();
			
		}
		else {
			driver.quit();
		}
	}
}
