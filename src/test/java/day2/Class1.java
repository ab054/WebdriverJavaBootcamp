package day2;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

public class Class1 {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        File file = new File("src/test/resources");
        String path = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", path + "/geckodriver" );
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
        //driver.findElement(By.cssSelector("a.gb_we.gb_Aa.gb_Fb"));
        //CssSelector tag_name + id
        //driver.findElement(By.cssSelector("a[id='gb_70']"));
        //Partial link text
        //driver.findElement(By.partialLinkText("Sign"));
        driver.findElement(By.id("identifierId")).sendKeys("bootcampPortnov@gmail.com");

        //driver.findElement(By.xpath("//span[.='Next']")).click();
        //same as above
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

    @Test
    public void testDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 2");
        WebElement option = dropdown.getFirstSelectedOption();
        String actualText = option.getText();
        Assert.assertEquals(actualText, "Option 2");
    }

    @Test
    public void testAlerts() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement btn = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        btn.click();
        String textFromAlert = driver.switchTo().alert().getText();
        Assert.assertEquals(textFromAlert, "I am a JS Confirm");
    }

    @Test
    public void testLoginJavaScript() {
        driver.get("https://the-internet.herokuapp.com/login");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('username').value='tomsmith'");
        js.executeScript("document.getElementById('password').value='SuperSecretPassword!'");
        String actualResult = driver.findElement(By.id("flash")).getText();
        String expectedResult = "You logged into a secure area!";
        Assert.assertEquals(actualResult, expectedResult);
    }

    // 1st test: valid email -> next = we see password screen
    @Test
    public void testValidEmail() {
        driver.get("https://google.com/accounts");
        driver.findElement(By.id("identifierId")).sendKeys("alekseipetrovski");
        driver.findElement(By.xpath("//span[text() = 'Next']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBe(By.id("headingText"), "Welcome1"));
    }

    // 2nd test: click forget email -> next = we see find your email screen
    @Test
    public void testForgotEmail() {
        driver.get("https://google.com/accounts");
        driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBe(By.id("headingText"), "Find your email"));
    }

    // 3rd test: invalid email = couldn't find your google acc
    // 4rd test: create account link

    // 5th test: 0 characters -> next = enter email ... error message
    @Test
    public void test0HandlingCharacters() {
        driver.get("https://google.com/accounts");
        driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
        Boolean containsText = driver.getPageSource().contains("Enter an email or phone number");
        Assert.assertTrue(containsText);
    }


    // 6th test: click learn more

    // 7th test: Dropdown select value
    @Test
    public void dropDown() {
        driver.get("https://google.com/accounts");
        driver.findElement(By.xpath("//div[@jsname='LgbsSe']")).click();
        driver.findElement(By.xpath("//div[@class='OA0qNb ncFHed']/div[@data-value='da']")).click();
        String pageTitle = driver.findElement(By.id("headingText")).getText();
        Assert.assertEquals(pageTitle, "Log ind");
    }


    // 8th test: Help -> new tab
    @Test
    public void testTabHandlers() throws Exception {
        driver.get("https://google.com/accounts");
        driver.findElement(By.linkText("Help")).click();
        String childWindow = null;
        String parentWindow = driver.getWindowHandle();
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("material-bar-custom-product-name")));
        String newWindowText = driver.findElement(By.id("material-bar-custom-product-name")).getText();
        System.out.println("Text from new window: " + newWindowText + "\n");
        driver.close();
        driver.switchTo().window(parentWindow);
    }

    @Parameters({"param1"})
    @Test
    public void forgotPasswordParam(String testExpectedMessage) {
        String pageAlert = testExpectedMessage;

        driver.get("https://the-internet.herokuapp.com/forgot_password");
        driver.findElement(By.id("email")).sendKeys("my@email.com");
        driver.findElement(By.id("form_submit")).click();
        WebElement element = driver.findElement(By.id("content"));
        String result = element.getText();
        Assert.assertEquals(result, pageAlert);
    }

    @Parameters({"paramTwo"})
    @Test
    public void oneMoreParam(String str) {
        System.out.println(str);
    }

    @DataProvider(name = "dataProviderName")
    public Object[][] createData1() {
        return new Object[][] {
                { "Cedric", 36},
                { "Anne", 37},
                { "myName", 59}
        };
    }

    @Test(dataProvider = "dataProviderName")
    public void verifyData1(String n1, int n2) {
        System.out.println(n1 + " " + n2);
    }
}