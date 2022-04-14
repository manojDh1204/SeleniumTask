package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyStepdefs {

    public WebDriver driver ;

    String driverPath = "C://Automation//100//chromedriver.exe";
        WebDriverWait wait =  new WebDriverWait(driver,20);

    @Given("I have browser and i open a {string}")
    public void iHaveBrowserAndIOpenAUrl(String url) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        String URL= driver.getCurrentUrl();
        System.out.print(URL);

    }

    @And("I validate the Tile as {string}")
    public void iValidateTheTileAs(String title) {
        System.out.print(driver.getTitle());
        Assert.assertTrue(title.equalsIgnoreCase(driver.getTitle()));
    }

    @And("I Navigate to portal and Identify Broken link On the poratl")
    public void iNavigateToPortalAndIdentifyBrokenLinkOnThePoratl() {
        int count = 0;
        List<WebElement> links = null;
        List<WebElement> Pages = get_all_pages();
        for (WebElement page : Pages) {
         try {
                page.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a")));
                links = driver.findElements(By.xpath("//a"));
             } catch (StaleElementReferenceException ser){
                 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a")));
                 links = driver.findElements(By.xpath("//a"));
            }
        }

        System.out.println("Total  links :"+links.size());


        for( WebElement link: links)
        {
            driver.get(link.toString());
            try {
                if (driver.findElement(By.xpath("//h1[contains(text(),'404')]")).isDisplayed()) {
                    count++;
                }
            }catch(NoSuchElementException nse){
                System.out.println("Element in Not found");
            }
        }
        System.out.println("Total Broken links :"+count);
    }

    private List<WebElement> get_all_pages() {

        return driver.findElements(By.xpath("//a[@class= 'mega-menu-link']"));

    }
}
