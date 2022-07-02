package cucumber.steps;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

public class SampleStep {

    public WireMockRule wireMockRule = new WireMockRule(9000);

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
        $(By.id("result")).shouldBe(exactText(text));
    }
}