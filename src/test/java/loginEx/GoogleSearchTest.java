package loginEx;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class GoogleSearchTest {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver");
        driver = new FirefoxDriver();
    }


    @AfterTest
    public void closeDrive() {
        driver.quit();
    }

    @Parameters({ "queryParameter" })
    @Test
    public void test001(String queryString) throws Exception {
        navigatingToMainPage();
        typeRequestInSearchInput(queryString);
        submitSearch();
        printOutAmountOfResult();
    }

    private void printOutAmountOfResult() {
        String res = driver.findElement(MainPage.resultStats).getText();
        System.out.println(res);
    }

    private void submitSearch() {
        driver.findElement(MainPage.submitButton).click();
    }

    private void typeRequestInSearchInput(String queryString) {
        driver.findElement(MainPage.searchInput).sendKeys(queryString);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void navigatingToMainPage() {
        String url = "http://google.com";
        driver.get(url);
    }
}
