package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Class1 {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.gecko.driver", "/Users/abarabash/Documents/MyProject/WebdriverJavaBootcamp/src/test/resources/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void test001(){
        driver.get("http://google.com");
        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Portnov computer school");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@value='Google Search']")).click();

        String res = driver.findElement(By.id("resultStats")).getText();
        System.out.println(res);
    }

    @Test
    public void googleSignin() {
        driver.get("http://google.com");
        driver.findElement(By.id("gb_70")).click();

        //CssSelector tag_name + class name
//        driver.findElement(By.cssSelector("a.gb_we.gb_Aa.gb_Fb"));
        //CssSelector tag_name + id
//        driver.findElement(By.cssSelector("a[id='gb_70']"));
        //Partial link text
//        driver.findElement(By.partialLinkText("Sign"));
        driver.findElement(By.id("identifierId")).sendKeys("bootcampPortnov@gmail.com");


//        driver.findElement(By.xpath("//span[.='Next']")).click();
//        same as above
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        driver.findElement(By.xpath("//input[type='password']")).sendKeys("abcdef1234@");
        driver.findElement(By.xpath("//span[text()='Next']")).click();





    }




















    // HomeWork
//    @Test
//    public void testInterface() throws Exception {
//        Selenium driver = new SeleniumAleksDriver();
//        //todo
//        Selenium driverMine = new SeleniumMyOwnDriver();
//        driver.get();
//        driver.anotherMethod();
//
//        driverMine.anotherMethod();
//        driverMine.get();
//    }
}