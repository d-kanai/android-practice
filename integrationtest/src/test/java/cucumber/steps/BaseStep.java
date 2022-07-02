package cucumber.steps;

import cucumber.AndroidApp;
import io.cucumber.java.en.Given;

public class BaseStep {

    @Given("Open App")
    public void openApp() {
        AndroidApp.launch();
    }

}
