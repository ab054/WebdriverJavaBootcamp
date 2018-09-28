package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class Class1 {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        File file = new File("src/test/resources/");
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath + "/geckodriver");
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


    @Test
    public void forgotPassword() {
        driver.get("https://the-internet.herokuapp.com/forgot_password");
        driver.findElement(By.id("email")).sendKeys("my@email.com");
        driver.findElement(By.id("form_submit")).click();
        WebElement element = driver.findElement(By.id("content"));
        String result = element.getText();
        Assert.assertEquals(result, "Your e-mail's been sent!!");
    }

    @Test
    public void visibilityOfElementLocated() throws Exception {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/H4")));


        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/H4")).getText(), "Hello World!");
    }

    @Test
    public void presenceOfElement() throws Exception {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish']/H4")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/H4")).getText(), "Hello World!");
    }

    @Test
    public void disappearingElement() throws Exception {
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
        if(driver.findElement(By.linkText("Gallery")).isDisplayed()) {
            driver.findElement(By.linkText("Gallery")).click();
        }
    }

    @Test
    public void testBaseAuth() throws Exception {
        String url = "the-internet.herokuapp.com/basic_auth";
        String userName = "admin";
        String password = "admin";
        String stringToVerify = "Congratulations! You must have the proper credentials.";
        String urlWithAuth = "https://" + userName + ":" + password + "@" + url;

        driver.get(urlWithAuth);
        boolean result = driver.getPageSource().contains(stringToVerify);
        Assert.assertTrue(result);
    }

    @Test
    public void testheckboxes() throws Exception {
        String url = "https://the-internet.herokuapp.com/checkboxes";

        driver.get(url);

        List<WebElement> elementList = driver.findElements(By.cssSelector("input[type='checkbox']"));

        for(WebElement each : elementList){
            System.out.println(each.getAttribute("checked"));
        }

        String secondCheckboxChecked = elementList.get(1).getAttribute("checked");

        Assert.assertEquals(secondCheckboxChecked, "true");

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