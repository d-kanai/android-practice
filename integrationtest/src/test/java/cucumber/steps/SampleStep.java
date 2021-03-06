package cucumber.steps;

import cucumber.Assert;
import io.appium.java_client.MobileBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SampleStep {

    @When("I fill in {string} to text field")
    public void iFillInToTextField(String text) {
        $(By.id("edit_text_dod_name")).setValue(text); //Android
    }

    @And("I click the button")
    public void iClickTheButton() {
        $(By.id("dod_create_button")).click();
    }

    @Then("I should see the text {string}")
    public void iShouldSeeTheText(String text) {
        $(By.id("dod_list_view")).isDisplayed();
        Assert.containTextInAndroidScreen(text);
    }

    @When("I click Count Up Button")
    public void iClickCountUpButton() {
        webdriver().driver().getWebDriver().findElement(MobileBy.AccessibilityId("countUpButton")).click();
    }

    @Then("I should see the text {string} in IOS screen")
    public void iShouldSeeTheTextInIOSScreen(String text) {
        String textViewString = String.format("label=\"%s\"", text);
        String pageSource = getWebDriver().getPageSource();
        assertThat(pageSource, is(containsString(textViewString)));
    }
}