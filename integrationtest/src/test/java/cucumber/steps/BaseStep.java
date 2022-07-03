package cucumber.steps;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import cucumber.AndroidApp;
import io.cucumber.java.en.Given;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;

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
