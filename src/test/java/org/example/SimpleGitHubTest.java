package org.example;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

public class SimpleGitHubTest {

    @BeforeEach
    public void initDriver() throws IOException {
        var ip = "127.0.0.1";
        var driver = new RemoteWebDriver(new URL(format("http://%s:4444/wd/hub/", ip)), DesiredCapabilities.chrome());
        driver.manage().window().setSize(new Dimension(1920, 1024));
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterEach
    public void stopDriver() {
        Optional.ofNullable(WebDriverRunner.getWebDriver()).ifPresent(WebDriver::quit);
    }

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
