package DemoQA_usingTestNG;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ClassForIndividualTestCase {

	WebDriverWait wait;
	WebDriver driver;

	@BeforeTest
	public void BeforeAll() {
		System.out.println("Setup executed before all tests.");
		WebDriverManager.chromedriver().setup();                  // Automatically sets up the driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@Test
	public void Menu() {
		driver.get("https://demoqa.com/menu");
		driver.findElement(By.xpath(("//a[normalize-space()='Main Item 1']"))).click();
	}
	
	@Test(description = "move To Element & perform")
    public void testMenuNavigation() throws InterruptedException {
        driver.get("https://demoqa.com/menu");
        Actions actions = new Actions(driver);

        WebElement mainItem2 = driver.findElement(By.xpath("//a[text()='Main Item 2']"));   // Hover over "Main Item 2"
        actions.moveToElement(mainItem2).perform();

        WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        actions.moveToElement(subSubList).perform();

        WebElement subSubItem2 = driver.findElement(By.xpath("//a[text()='Sub Sub Item 2']"));
        actions.moveToElement(subSubItem2).click().perform();

        Thread.sleep(2000); 
        System.out.println(driver.getCurrentUrl());
        AssertJUnit.assertTrue(subSubItem2.isDisplayed());      // Assertion to check expected behavior
        
    }
	
}






