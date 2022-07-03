package cucumber.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.AndroidApp;
import io.cucumber.java.en.Given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseStep {

    WireMockServer wireMockServer = new WireMockServer(9000);

    @Given("Open App")
    public void openApp() {
        AndroidApp.launch();
    }

    @Given("Set stub response")
    public void setStubResponse() {
        wireMockServer.start();
        wireMockServer.stubFor(get("/dods")
                .willReturn(ok()
                        .withBody("{\"items\":[]}")));
        wireMockServer.stubFor(post("/dods")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"name\":\"Long Method\"}"))
        );
    }
}
