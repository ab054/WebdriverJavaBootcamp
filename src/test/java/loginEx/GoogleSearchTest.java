package loginEx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    private WebDriver driver;


    @BeforeTest(groups = {"fast", "slow"})
    public void setupDriver() {
        System.setProperty("webdriver.gecko.driver", "/Users/abarabash/Documents/MyProject/WebdriverJavaBootcamp/src/test/resources/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test(groups = {"fast"})
    public void test001() throws Exception {
        navigatingToMainPage();
        typeRequestInSearchInput();
        submitSearch();
        printOutAmountOfResult();
    }

    @Test(groups = {"slow"})
    public void test002() throws Exception {
        navigatingToMainPage();
        typeRequestInSearchInput();
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

    private void typeRequestInSearchInput() {
        driver.findElement(MainPage.searchInput).sendKeys("Portnov computer school");
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
