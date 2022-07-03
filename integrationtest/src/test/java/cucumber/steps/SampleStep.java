package cucumber.steps;

import cucumber.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SampleStep {

    @When("I fill in {string} to text field")
    public void iFillInToTextField(String text) {
        $(By.id("edit_text_dod_name")).setValue(text);
    }

    @And("I click the button")
    public void iClickTheButton() {
        $(By.id("dod_create_button")).click();
    }

    @Then("I should see the text {string}")
    public void iShouldSeeTheText(String text) {
        $(By.id("dod_list_view")).isDisplayed();
        Assert.containTextInScreen(text);
    }

}