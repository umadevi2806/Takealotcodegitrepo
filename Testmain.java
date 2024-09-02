package Automation.takealot;

import java.io.Console;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.asserts.Assertion;

public class Testmain {
    
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); 
        wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.manage().window().maximize(); 
    }

    @Test
    public void testAddItemToCart() throws InterruptedException {
        String url = "https://www.takealot.com";
        String SamsungFridgeitem = "Samsung Fridge"; 
        driver.get(url);
        
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));         
        searchBox.sendKeys(SamsungFridgeitem);
        searchBox.submit();
        
        WebElement closinggotitnotification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Got it')]")));
        closinggotitnotification.click();
        
        WebElement closingnotnownotification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'NOT NOW')]")));
        closingnotnownotification.click();
        
        WebElement addingitemtocart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='shopfront-app']/div[4]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/button[1]")));
        addingitemtocart.click();
        Thread.sleep(1000);
        
        WebElement verifyingnumberofitemsincart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]/div[2]/div[1]")));
        verifyingnumberofitemsincart.getText();
        Thread.sleep(5000);
		String actualvalue = verifyingnumberofitemsincart.getText();
		String expectedvalue = "1";
		System.out.println("Actual value is :" + actualvalue);
		System.out.println("Expected value is :" + expectedvalue);
		if(actualvalue.equals(expectedvalue))
		{
			System.out.println("Both are equal...");
		}
		else
		{
			System.out.println("Both are not equal...");
		}  
		
		Assert.assertEquals(actualvalue, expectedvalue);
		
		WebElement cartclicking = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='shopfront-app']/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")));
		Thread.sleep(1000);
		cartclicking.click();
		
		WebElement verifyingitemincart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Samsung 647L Side by Side Fridge with Space Max Te')]")));
		verifyingitemincart.getText();
        Thread.sleep(5000);
		String actualvalue1 = verifyingitemincart.getText();
		String expectedvalue1 = "Samsung 647L Side by Side Fridge with Space Max Technology";
		System.out.println("Actual value is :" + actualvalue1);
		System.out.println("Expected value is :" + expectedvalue1);
		
		
		if(actualvalue1.equals(expectedvalue1))
		{
			System.out.println("Both are equal...");
		}
		else
		{
			System.out.println("Both are not equal...");
		}  
		
    
        Assert.assertEquals(actualvalue1, expectedvalue1);
    }

	private WebElement findElement(By xpath) {
		return null;
	}

	@AfterClass
    public void tearDown() {
        if (driver != null) {
           driver.quit();
        }
    }
}
