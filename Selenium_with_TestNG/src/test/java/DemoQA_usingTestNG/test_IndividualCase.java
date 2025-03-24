package DemoQA_usingTestNG;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class test_IndividualCase {
	WebDriverWait wait;
	WebDriver driver;

	@BeforeTest
	public void BeforeAll() {
		System.out.println("Setup executed before all tests.");
		WebDriverManager.chromedriver().setup(); // Automatically sets up the driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	}

	@Test(description = "Multiple Window Handling")
	public void WindowMultiple() throws InterruptedException {
		driver.get("https://demoqa.com/browser-windows");

//		Thread.sleep(2000); 
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));

		driver.findElement(By.id(("windowButton"))).click();
		String mainWindowHandle = driver.getWindowHandle(); // current active window's handle (ID).
		System.out.println("Main Window GU ID : " + mainWindowHandle);

		Set<String> allWindowHandles = driver.getWindowHandles(); // dealing with multiple browser windows or tabs.

		Iterator<String> iterator = allWindowHandles.iterator(); // creates an iterator to go through all window
																	// handles.
		System.out.println(allWindowHandles);

		// while loop is iterating through multiple window handles & switching to the
		// child window.

		while (iterator.hasNext()) { // Check if there is another window
			String ChildWindow = iterator.next(); // Get next window handle

			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) { // Check if it's NOT the main window
				driver.switchTo().window(ChildWindow); // Switch to child window
				String text = driver.findElement(By.id("sampleHeading")).getText();
				AssertJUnit.assertTrue(text.contains("This is a sample page")); // verifies the expected string.

				System.out.println("Child Window GU ID : " + ChildWindow);
				System.out.println("Child Window URL : " + driver.getCurrentUrl());
			}

//			driver.close();
//			driver.switchTo().window(mainWindowHandle);
		}
	}

	@Test(description = "Extract First Name & Salary from Web Table")
	void extractNameAndSalary() {
		driver.get("https://demoqa.com/webtables");

		WebElement table = driver.findElement(By.className("rt-tbody"));
		List<WebElement> rows = table.findElements(By.className("rt-tr"));

		System.out.println("First Name | Salary");
		System.out.println("-------------------");

		for (WebElement row : rows) {
			try {
				String firstName = row.findElement(By.xpath(".//div[@class='rt-td'][1]")).getText();
				String salary = row.findElement(By.xpath(".//div[@class='rt-td'][5]")).getText();

				System.out.println(firstName + " | " + salary); // Print the extracted data
			} catch (NoSuchElementException e) {
				System.out.println("Skipping empty row..."); // Handle case where row might be empty
			}
		}

	}

	@Test(description = "Date Picker from Keyboard")
	public void selectDatebySendKeys() {
		driver.get("https://demoqa.com/date-picker");
//		driver.findElement(By.id("datePickerMonthYearInput")).click();
//		driver.findElement(By.id("datePickerMonthYearInput")).clear();

//		Use Keys.chord(Keys.CONTROL, "a") for selecting all text properly.      //   (Keys.CONTROL + "A")
		driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
		driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("12/08/1995");
		driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
		System.out.println(driver.findElement(By.id("datePickerMonthYearInput")).getText());
	}

}
