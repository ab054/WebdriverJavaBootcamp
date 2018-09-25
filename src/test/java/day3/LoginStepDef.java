package day3;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hooks.Hooks;
import org.openqa.selenium.WebDriver;

public class LoginStepDef {

    public WebDriver driver;

    public LoginStepDef() {
        driver = Hooks.driver;
    }


    @When("^I tap on Login button$")
    public void iTapOnLoginButton() throws Throwable {
        driver.get("http://google.com");
    }

    @Then("^I type \"([^\"]*)\" into username field$")
    public void iTypeIntoUsernameField(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I type \"([^\"]*)\" into password field$")
    public void iTypeIntoPasswordField(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I verify user is logged in$")
    public void iVerifyUserIsLoggedIn() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
