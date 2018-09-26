package YahooSearch;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YahooSearchTest {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void closeDrive() {
        driver.quit();
    }

    @Test
    public void testSearch() throws Exception {
        navigatingToMainPage();
        typeRequestInSearchInput();
        submitSearch();
        printOutAmountOfResult();
    }

    private void typeRequestInSearchInput() {
    }

    private void navigatingToMainPage() {
        driver.get("https://yahoo.com");
    }

    private void submitSearch() {
    }

    private void printOutAmountOfResult() {
    }


}
