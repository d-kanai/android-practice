package cucumber.steps;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class Assert {

    static void containTextInScreen(String text) {
        String textViewString = String.format("TextView\" text=\"%s\"", text);
        String pageSource = getWebDriver().getPageSource();
        assertThat(pageSource, is(containsString(textViewString)));
    }
}
