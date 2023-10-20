package Greenmouse.BOG.reuseables;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class launchApp {

	WebDriver driver;

	public WebDriver initializeDriver() throws IOException

	{

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);

		driver = new ChromeDriver(options);

		return driver;
	}

	public void launch() throws InterruptedException, IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();

		driver.get("https://bog-project-new.netlify.app/");

		Thread.sleep(3000);

		// driver.findElement(By.xpath("//*[contains(text(),'Got It')]")).click();
		driver.findElement(By.xpath("//button[text()='Got It']")).click();

	}

	public void login() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.id("email")).sendKeys("greenmouseapp@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href='/dashboard/switch']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Switch'])[2]")));
		driver.findElement(By.xpath("(//button[text()='Switch'])[2]")).click();
		driver.findElement(By.cssSelector("img[alt='boglogo']")).click();
		
	}

	public void buy() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[text()='Products']")).click();
		
		//scroll window
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 300)");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".grid-2")));

		driver.findElement(By.xpath("//p[text()='QA House']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add To Cart']")));

		driver.findElement(By.xpath("//button[text()='Add To Cart']")).click();

		driver.findElement(By.xpath("//a[@href='/carts'][1]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='PROCEED TO CHECKOUT']")));

		driver.findElement(By.xpath("//p[text()='PROCEED TO CHECKOUT']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".max-h-103")));

		driver.findElement(By.id("contact_name")).sendKeys("Daniel");

		driver.findElement(By.id("contact_email")).sendKeys("greenmousetest+client@gmail.com");

		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("07065123745");

		driver.findElement(By.id("state")).sendKeys("Ebonyi");
		Thread.sleep(8000);
		driver.findElement(By.id("home_address")).sendKeys("2 metalbox Rd");
		
		WebElement selectText = driver.findElement(By.id("nearest_address"));
		
		//wait.until(ExpectedConditions.attributeContains(selectText, "ABAKALIKI, ebonyi", "ABAKALIKI, ebonyi"));
		
		Select opt = new Select(selectText);
		opt.selectByIndex(1);

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='CHECKOUT']")).click();

		// WebDriverWait of Explicit Wait
		// WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(40));

		// FluentWait Explicit Wait
		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		// w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card__selec")));

		driver.switchTo().frame("root");

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card__select")));

		driver.findElement(By.cssSelector(".card__select")).click();

	}

	public void requestService() throws InterruptedException
	{
		driver.findElement(By.xpath("(//a[@href='/services'][1])")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[text()='QA building']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Wiring']")));
		
		driver.findElement(By.xpath("//div[text()='Wiring']")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='text-1686040481646']")));
		driver.findElement(By.cssSelector("input[name='text-1686040481646']")).sendKeys("DANIEL");
		driver.findElement(By.cssSelector("input[name='text-1686040482738']")).sendKeys("2 METALBOX ROAD");
		driver.findElement(By.cssSelector("input[name='number-1686040485289']")).sendKeys("08077767656");
		driver.findElement(By.cssSelector("textarea[name='textarea-1686040487172']"))
		.sendKeys("This project is for full house wiring with standard materials");
		driver.findElement(By.cssSelector("input[name='text-1686040488294']")).sendKeys("IKEJA LAGOS");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		
		//driver.switchTo().alert().accept();
		Thread.sleep(5000);
		
		//To go back and Delete the request
		driver.findElement(By.xpath("//button[text()='Dashboard']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[text()='QA Product Green']")));
		driver.findElement(By.xpath("//p[text()='Projects']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//p[text()='My Projects'])[2]")));
		
		//WebElement today = driver.findElement(By.xpath("//tbody/tr[1]/td[6]"));
		
		//Date d = new Date();
		//d.toString();
		//SimpleDateFormat sdf = SimpleDateFormat("M/d/yyyy");
		
		//System.out.println(sdf.format(d));
		
		//String pattern = "MM/dd/yyyy";
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		//System.out.println(simpleDateFormat.format(d));
		
		  // Locate the WebElement containing the date
		WebElement dateElement = driver.findElement(By.xpath("//tbody/tr[1]/td[6]"));

	    // Get the date text from the WebElement
	    String dateText = dateElement.getText();

	    // Parse the date text into a LocalDate object
	    LocalDate webElementDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

	    
	 // Get the current date
	    LocalDate currentDate = LocalDate.now();

	    // Format the current date as per your requirements
	   // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    //String formattedCurrentDate = currentDate.format(formatter);


	    // Compare the dates
	    if (currentDate.isEqual(webElementDate)) {
	        System.out.println("The dates are equal.");
	    } else if (currentDate.isBefore(webElementDate)) {
	        System.out.println("The current date is before the WebElement date.");
	    } else {
	        System.out.println("The current date is after the WebElement date.");
	    }

	
	}
	
	
	
	
	
	public void tearDown() {
		driver.close();
	}

}
