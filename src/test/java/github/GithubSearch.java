package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GithubSearch {
    static {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeAll
    static public void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy ="eager";
    }

    @Test
    void jUnit5Test() {
        open("https://github.com/selenide/selenide/");

        String textToFind = """ 
                Using JUnit5 extend test class:
                @ExtendWith({SoftAssertsExtension.class})
                  class Tests {
                    @Test
                 """;

        // - Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".wiki-more-pages-link button").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        // Откройте страницу SoftAssertions
        $("#wiki-pages-box [href*=" + "SoftAssertions" + "]").click();

        // Проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave(text(textToFind));

    }
}
