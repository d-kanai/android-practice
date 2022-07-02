package cucumber.steps;

import cucumber.AndroidApp;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.java.en.Given;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseStep {

    public WireMockRule wireMockRule = new WireMockRule(9000);

    @Given("Open App")
    public void openApp() {
        AndroidApp.launch();
    }

    @Given("Set stub response")
    public void setStubResponse() {
        wireMockRule.stubFor(get("/dods")
                .willReturn(ok()
                        .withBody("{\"items\":[]}")));
    }
}
