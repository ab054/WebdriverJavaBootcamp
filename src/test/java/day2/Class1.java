package day2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Class1 {

    @Test
    public void test001(){
        System.setProperty("webdriver.gecko.driver", "/Users/abarabash/Documents/MyProject/WebdriverJavaBootcamp/src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();
        driver.get("http://google.com");
    }

    @Test
    public void testInterface() throws Exception {
        Selenium driver = new SeleniumAleksDriver();
        //todo
        Selenium driverMine = new SeleniumMyOwnDriver();
        driver.get();
        driver.anotherMethod();

        driverMine.anotherMethod();
        driverMine.get();
    }
}