package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SimpleGitHubTest {

    @Test
    public void testIssue() {
        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Go to repository page", () -> {
            $x("//*[contains(@class, 'header-search-input')]").click();
            $x("//*[contains(@class, 'header-search-input')]")
                    .sendKeys("dstekanov/selenoid-docker-compose-example");
            $x("//*[contains(@class, 'header-search-input')]").submit();
            $x("//a[@href='/dstekanov/selenoid-docker-compose-example']").click();
        });
        step("Verify About title", () -> {
            $("[href='/dstekanov/selenoid-docker-compose-example']").click();
            $(".BorderGrid-row .f4").shouldHave(text("Hello, Selenoid!"));
        });

    }
}
