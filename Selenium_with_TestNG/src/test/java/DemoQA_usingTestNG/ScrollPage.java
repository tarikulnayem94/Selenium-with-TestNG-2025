package DemoQA_usingTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Test
public class ScrollPage {

	WebDriver driver;
	JavascriptExecutor js;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\Driver_chrome_133\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
	}

	@Test (description = "Smooth scrolling")
	public void scroll() throws InterruptedException {

		driver = new ChromeDriver();
		driver.get("https://demoqa.com/");
		driver.manage().window().maximize();
		                                        // Scroll down smoothly
		smoothScroll(500, true);   // Scroll down in 500px steps
		Thread.sleep(2000); 
		                                     // Scroll back up smoothly
		smoothScroll(-500, false);   // Scroll up in 500px steps
		Thread.sleep(2000); 
	}

	private void smoothScroll(int step, boolean scrollDown) throws InterruptedException {
	    // Get the scroll height
	    Number scrollHeight = (Number) js.executeScript("return document.body.scrollHeight;");
	    Number currentPosition = (Number) js.executeScript("return window.pageYOffset;");

	    if (scrollDown) {
	        while (currentPosition.doubleValue() < scrollHeight.doubleValue()) {
	            js.executeScript("window.scrollBy(0, arguments[0]);", step);
	            currentPosition = (Number) js.executeScript("return window.pageYOffset;");
	            Thread.sleep(500); // Adjust speed if needed
	        }
	    } else {
	        while (currentPosition.doubleValue() > 0) {
	            js.executeScript("window.scrollBy(0, -arguments[0]);", step);
	            currentPosition = (Number) js.executeScript("return window.pageYOffset;");
	            Thread.sleep(500);
	        }
	    }
	}

}








