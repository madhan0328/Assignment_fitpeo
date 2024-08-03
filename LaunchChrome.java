package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchChrome {

	public static void main(String[] args) throws InterruptedException {
        
    	// Use WebDriverManager to setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the specified URL
        driver.get("https://www.fitpeo.com/");

        // Print the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Navigate to the Revenue Calculator page
        driver.navigate().to("https://fitpeo.com/revenue-calculator");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        
         // Scroll down the Revenue Calculator page
        js.executeScript("window.scrollBy(0, 400)");
        	
        WebElement slider = driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/span[1]"));
        WebElement handle = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]"));

        // Get the dimensions of the slider and handle
        int sliderWidth = slider.getSize().getWidth(); // Full width of the slider
        int handleWidth = handle.getSize().getWidth(); // Width of the handle
        int sliderLeft = slider.getLocation().getX(); // X position of the slider
        int handleLeft = handle.getLocation().getX(); // X position of the handle

        // Define the desired value and calculate the target position
        int desiredValue = 820;
        int maxValue = 2000;
        int sliderTrackLength = sliderWidth - handleWidth; // Length of the slider track

        // Calculate the target position on the slider track
        int targetPosition = (int) ((desiredValue / (double) maxValue) * sliderTrackLength);

        // Calculate the movement required
        int currentPosition = handleLeft - sliderLeft; // Current position of the handle on the slider
        int moveBy = (targetPosition - 2) - (currentPosition  - 1 );
        Thread.sleep(2000);


        // Perform the drag action
        Actions actions = new Actions(driver);
        actions.clickAndHold(handle).moveByOffset(moveBy, 0).release().perform();
        
        Thread.sleep(2000);
        
       WebElement inputField = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div/div/div/input"));
        Thread.sleep(3000);
        inputField.click();
        Thread.sleep(3000);

        js.executeScript("arguments[0].value = '';", inputField);
        Thread.sleep(3000);

        js.executeScript("arguments[0].value = '560';", inputField);

        // Send new keys
        inputField.sendKeys("560");
        
        js.executeScript("arguments[0].value = '';", inputField);
        js.executeScript("window.scrollBy(0, 200)");
        
        Thread.sleep(3000);

         driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
         Thread.sleep(3000);

         driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
         Thread.sleep(3000);	

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[8]/label/span[1]/input")).click();
        Thread.sleep(3000);

      WebElement value =  driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/p[4]/p"));
      Thread.sleep(3000);

      String expectedReimbursement = "$110700";
      String actualReimbursement = value.getText();

      if (expectedReimbursement.equals(actualReimbursement)) {
          System.out.println("Total Recurring Reimbursement is correct: " + actualReimbursement);
      } else {
          System.out.println("Total Recurring Reimbursement is incorrect. Expected: " + expectedReimbursement + ", but found: " + actualReimbursement);
      }

        
       // driver.quit();
    }
}


