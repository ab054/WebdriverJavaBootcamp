package day2;

import java.util.concurrent.TimeUnit;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.Set;
import java.io.File;

public class Class1 {


    private WebDriver driver;

    @BeforeTest
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver");

        driver = new FirefoxDriver();

    }

    @AfterTest
    public void closeSelenium() {
        //  driver.quit();
    }

    @Test
    public void googleSearch() {
        driver.get("https://google.com");
        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Portnov Computer School");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@value='Google Search']")).click();
        driver.findElement(By.id("resultStats")).click();
        driver.findElement(By.id("resultStats")).getText();
    }

    @Test
    public void signinToAccount() {
//        driver.findElement(By.cssSelector("a[id=gb_70]")).click();
//        driver.findElement(By.cssSelector("a.gb_we.gb_Aa.gb_Fb")).click();
//        driver.findElement(By.id("gb_70")).click();
//        driver.findElement(By.partialLinkText("ServiceLogin")).click();
        driver.findElement(By.partialLinkText("Sign")).click();
        driver.findElement(By.id("identifierId")).sendKeys("email@gmail.com");


//        driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
        // here is going to be a problem.. multiple elements with same css
//        driver.findElements(By.xpath("//content[@class='CwaK9']/span"));
//        also not going to work by same reason
        driver.findElement(By.xpath("//span[.='Next']")).click();

        driver.findElement(By.xpath("//input[type='password']"));

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("root1234");
        driver.findElement(By.xpath("//span[.='Next']")).click();


    }

    @Test
    public void basicAuth() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.getPageSource().contains("Congratulations! You must have the proper credentials."));
    }

  //DONE
    @Test
    public void forgotPassword() {
        driver.navigate().to("http://the-internet.herokuapp.com/forgot_password");
        driver.findElement(By.id("email")).sendKeys("*********@gmail.com");
        driver.findElement(By.id("form_submit")).click();

        WebElement confirmation = driver.findElement(By.id("content"));
        String text = confirmation.getText();
        Assert.assertEquals(text, "Your e-mail's been sent!");
    }

    @Test
    public void checkboxes(){
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkBoxList=driver.findElements(By.cssSelector("input[type='checkbox']"));

        for(WebElement checkBox: checkBoxList)
        {
            System.out.println(checkBox.getText());
            if ( !checkBox.isSelected() )
            {
                //comment this line after and execute test, did it fail?
                checkBox.click();
            }
        }

        // Assert if any checkbox left checked
        List<WebElement> allCheckedBoxList=driver.findElements(By.xpath("//input[@type='checkbox' and @checked='checked']"));
        if(!allCheckedBoxList.isEmpty()) {
            Assert.fail();
        }
    }

    @Test
    public void Dropdown(){
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 2");

        WebElement option = dropdown.getFirstSelectedOption();
        String actualText = option.getText();
        Assert.assertEquals(actualText,"Option 2");
    }

    @Test
    public void DisappearingElements(){
        driver.get("http://the-internet.herokuapp.com/disappearing_elements");
        if(driver.findElement(By.linkText("Gallery")).isDisplayed()){
            driver.findElement(By.linkText("Gallery")).click();
        }
    }

    @Test
    public void DynamicallyLoadedPageElements1(){

        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/H4")));
        WebElement e = driver.findElement(By.xpath("//div[@id='finish']/H4"));

        Assert.assertEquals(e.getText(), "Hello World!");
    }

    @Test
    public void DynamicallyLoadedPageElements2(){

        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2 ");

        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //presenceOfElementLocated - Verify presence of element in the DOM.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish']/H4")));
        WebElement e = driver.findElement(By.xpath("//div[@id='finish']/H4"));

        Assert.assertEquals(e.getText(), "Hello World! ");
    }

    @Test
    public void testWebTables() {
        driver.navigate().to("https://the-internet.herokuapp.com/tables");
        WebElement table1 = driver.findElement(By.id("table1"));
        table1.findElement(By.xpath("//table[@id='table1']//td[contains(text(),'jdoe@hotmail.com')]"));
        table1.findElement(By.xpath("//table[@id='table1']//tbody//tr[3]//td[6]//a[2]")).click();
    }


    @Test
    public void testAlerts() {
        driver.navigate().to("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement jsConfirmBTN = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        jsConfirmBTN.click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void testSwitchToNewWindow() {
        driver.navigate().to("https://the-internet.herokuapp.com/windows");
        String childWindow = null;
        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();

        // do not add wait for first time
        // add it after test fails
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));


        Set<String> allWindows = driver.getWindowHandles();
        int count = allWindows.size();
        System.out.println("Windows Count: " + count);

        for (String temp : allWindows) {
            if (!temp.equals(parentWindow)) {
                childWindow = temp;
            }
        }

        System.out.println("Parent Window: " + parentWindow);
        System.out.println("Child Window: " + childWindow + "\n");

        driver.switchTo().window(childWindow);

        String newWindowText = driver.findElement(By.xpath("//div[@class='example']")).getText();
        System.out.println("Text from new window: " + newWindowText + "\n");
        driver.close();
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void hovers() {
        driver.navigate().to("http://the-internet.herokuapp.com/hovers");

        //User1
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
        Actions hover1 = new Actions(driver);
        hover1.moveToElement(element1).build().perform();
        driver.findElement(By.linkText("View profile")).click();

        //User2
        driver.navigate().back();
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img"));
        Actions hover2 = new Actions(driver);
        hover2.moveToElement(element2).build().perform();
        driver.findElement(By.linkText("View profile")).click();

        //User3
        driver.navigate().back();
        WebElement element3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/img"));
        Actions hover3 = new Actions(driver);
        hover3.moveToElement(element3).build().perform();
        driver.findElement(By.linkText("View profile")).click();
        //Redundancy in code can be avoided using @DataProvider
    }

    @Test
    public void loginWithValidCredential(){
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//button[@class='radius']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Welcome to the Secure Area."));
    }

    @Test
    public void loginWithValidCredentialJavaScript(){
        driver.get("http://the-internet.herokuapp.com/login");
        JavascriptExecutor JS = (JavascriptExecutor)driver;
        JS.executeScript("document.getElementById('username').value='tomsmith'");
        JS.executeScript("document.getElementById('password').value='SuperSecretPassword!'");
        driver.findElement(By.xpath("//button[@class='radius']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Welcome to the Secure Area."));
    }

    @Test
    public void loginWithInvalidCredential(){
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("aleksei@petrovski.com");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[@class='radius']")).click();

        Assert.assertTrue((driver.findElement(By.id("flash")).getText().contains("Your username is invalid!")));
    }

    @Test
    public void nestedFrames(){
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        List<WebElement> iframeElements = driver.findElements(By.tagName("frame"));
        //why found only 2 frames it there is multiple
        System.out.println("The total number of iframes are " + iframeElements.size());

        System.out.println(driver.switchTo().frame("frame-top").switchTo().frame("frame-left").getPageSource());
        driver.switchTo().defaultContent();
        System.out.println(driver.switchTo().frame("frame-top").switchTo().frame("frame-middle").getPageSource());
        driver.switchTo().defaultContent();
        System.out.println(driver.switchTo().frame("frame-top").switchTo().frame("frame-right").getPageSource());
        driver.switchTo().defaultContent();
        System.out.println(driver.switchTo().frame(1).getPageSource());
    }

    @Test
    public void iframe() {
        driver.get("http://the-internet.herokuapp.com/iframe");

        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        WebElement element = driver.findElement(By.tagName("body"));
        element.clear();
        element.sendKeys("Selenium WebDriver Bootcamp");
    }

    @Test
    public void largeDOM() {
        driver.get("http://the-internet.herokuapp.com/large");
        WebElement element = driver.findElement(By.xpath("//tr[@class='row-50']/td[@class='column-50']"));

        Assert.assertTrue(element.getText().contains("50.50"));
    }






    // FIX
//    @Test
//    public void JavaScriptonloadeventError() {
//        driver.get("http://the-internet.herokuapp.com/javascript_error");
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        String errorRetrievalScript = "return window.__webdriver_javascript_errors;";
//
//        System.out.println(js.executeScript(errorRetrievalScript));
//    }

















    // TODO
    // public void DynamicControls() {
    // public void ContextMenu() {
    // public void ExitIntent_HandleModalWindow() {
    // public void testFileUpload() {
    // public void infiniteScroll() throws InterruptedException







}
