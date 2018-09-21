package day2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Class1 {

    @Test
    public void test001() throws Exception {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("http://google.com");
    }
}
