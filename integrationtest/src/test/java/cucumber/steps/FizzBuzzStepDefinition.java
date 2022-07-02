package cucumber;

import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.exactText;

public class FizzBuzzStepDefinition {

    @Given("FizzBuzzアプリが起動されている")
    public void fizz_buzzアプリが起動されている() {
        AcceptanceTest.openMyApp();
    }

    @When("入力フィールドに{string}を入力する")
    public void 入力フィールドに入力する(String inputString) {
        $(By.id("input")).setValue(inputString);
    }

    @When("ボタンをクリックする")
    public void ボタンをクリックする() {
        $(By.id("button")).click();
    }

    @Then("ボタンの下に{string}という文字列が表示される")
    public void ボタンの下に正しい文字列が表示される(String resultString) {
        $(By.id("result")).shouldBe(exactText(resultString));
    }

}