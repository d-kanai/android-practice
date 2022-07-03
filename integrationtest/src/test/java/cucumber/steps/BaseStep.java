package cucumber.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import cucumber.AndroidApp;
import io.cucumber.java.en.Given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseStep {

    WireMockServer wireMockServer = new WireMockServer(9000);

    @Given("Open App")
    public void openApp() {
        AndroidApp.launch();
        wireMockServer.start();
    }

    @Given("Set stub response")
    public void setStubResponse() {
        wireMockServer.stubFor(get("/dods")
                .willReturn(ok()
                        .withBody("{\"items\":[]}")));
        wireMockServer.stubFor(post("/dods")
                .willReturn(ok()
                        .withBody("{\"name\":\"Long Method\"}")));
    }
}
