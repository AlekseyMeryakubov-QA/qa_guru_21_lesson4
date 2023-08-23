package ru.meryakubov;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class SelenideTest {

    @Test
    public void testIssueSearch(){
        open("https://github.com");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(By.partialLinkText("eroshenkoam/allure-example")).click();

        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);


    }
}
