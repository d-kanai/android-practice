package cucumber;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Assert {

    public static void containTextInScreen(String text) {
        String textViewString = String.format("TextView\" text=\"%s\"", text);
        String pageSource = getWebDriver().getPageSource();
        assertThat(pageSource, is(containsString(textViewString)));
    }
}
