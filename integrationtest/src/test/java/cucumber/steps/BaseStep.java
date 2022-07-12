package cucumber.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.AndroidApp;
import cucumber.IOSApp;
import io.cucumber.java.en.Given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseStep {

    WireMockServer wireMockServer = new WireMockServer(9000);

    @Given("Open Android App")
    public void openAndroidApp() {
        AndroidApp.launch();
    }

    @Given("Open IOS App")
    public void openIOSApp() {
        IOSApp.launch();
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
