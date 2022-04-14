package pages;

import java.time.Clock;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class sample {

    public String baseUrl = "https://appwrk.com/";
    String driverPath = "C://Automation//100//chromedriver.exe";
    public WebDriver driver ;

 @Test
 public void iNavigateToPortalAndIdentifyBrokenLinkOnThePoratl() throws InterruptedException {
     int count = 0;
     System.setProperty("webdriver.chrome.driver", driverPath);
     driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
     driver.manage().window().maximize();
     driver.get(baseUrl);

     List<WebElement> links = null;
     List<WebElement> Pages = get_all_pages(driver);
     for (WebElement page : Pages) {
         try {
             page.click();
             links = driver.findElements(By.xpath("//a"));
         } catch (StaleElementReferenceException ser){
             links = driver.findElements(By.xpath("//a"));
         }
     }

     System.out.println("Total  links :"+links.size());


     for( WebElement link: links)
     {
         try {
         System.out.println("links:"+link.getAttribute("href").toString());
         driver.get(link.getAttribute("href"));
             if (driver.findElement(By.xpath("//*[contains(text(),'404')]")).isDisplayed()) {
                 count++;
             }else{
                 continue;
             }
         }catch(NoSuchElementException nse){
             System.out.println("Page Ling"+link.getAttribute("href")+ "- ok ");
         }catch(StaleElementReferenceException ste){
             Thread.sleep(5000);
             try {
             if (driver.findElement(By.xpath("//h1[contains(text(),'404')]")).isDisplayed()) {
                 count++;
             }else{
                 System.out.println("Page Ling"+link.getAttribute("href")+ "- ok ");
                 continue;
             }
             }catch(NoSuchElementException nse){
                 System.out.println("Page Ling"+link.getAttribute("href")+ "- ok ");
             }
         }
     }
     System.out.println("Total Broken links :"+count);
    }

    private List<WebElement> get_all_pages(WebDriver driver) {

        return driver.findElements(By.xpath("//a[@class= 'mega-menu-link']"));
    }

    @BeforeTest
    public void beforeTest() {

    }
    @AfterTest
    public void afterTest() {
        driver.quit();
        System.out.println("after test");
    }
}