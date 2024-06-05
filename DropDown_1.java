package Project_Sel;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DropDown_1 {

    WebDriver driver;
    String url = "https://phptravels.com/demo/";

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    @AfterTest
    public void tearDown(){

        //driver.quit();
    }
    @Test
    public void test1() throws IOException {
        //Using Explicit wait for submit button
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='whatsapp wow fadeIn animated']")));
        //Filling the form
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Logeshwaran");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("G");
        driver.findElement(By.xpath("//input[@name='business_name']")).sendKeys("Yaashu Private Limited");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("lokeshdharan1997@gmail.com");

        //Add logic for sum verification
        WebElement num1Ele = driver.findElement(By.xpath("//span[@id='numb1']"));
        WebElement num2Ele = driver.findElement(By.xpath("//span[@id='numb2']"));
        int num1 = Integer.parseInt(num1Ele.getText());
        int num2 = Integer.parseInt(num2Ele.getText());
        int sum = num1 + num2;

        WebElement captchaInput = driver.findElement(By.xpath("//input[@id='number']"));
        captchaInput.sendKeys(String.valueOf(sum));

        //Submitting the form
        driver.findElement(By.xpath("//button[@id='demo']")).click();

        //Verify the form is submitted successfully
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='text-center cw mt-3']/strong")));
        if (successMessage.isDisplayed()) {
            System.out.println("Form Submitted Successfully");

            //Take a screenshot
            TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("C:\\Users\\pjai0\\OneDrive\\Pictures\\Snaps\\Screenshot.png");
            ImageIO.write(ImageIO.read(srcFile), "png", destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        }else {
            System.out.println("Form Submission Failed");
        }


    }
}
