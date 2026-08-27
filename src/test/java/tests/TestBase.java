package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import components.CookieBannerComponent;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import pages.MainPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.utils.SelenideSetup;

import java.util.Map;


public class TestBase {
    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setupSelenideConfig() {

        // ✅ Видео и VNC — включены по умолчанию (Selenide сам обработает, где это возможно)
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true,
                "screenResolution", System.getProperty("browserResolution", "1920x1080")

        ));
        Configuration.browserCapabilities = capabilities;

        // Удалённый запуск — только если явно передан URL
        String remoteUrl = System.getProperty("remoteBrowserUrl");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            String login = System.getProperty("remoteBrowserUrlLogin", "user1");
            String password = System.getProperty("remoteBrowserUrlPassword", "1234");

            // Нормализуем URL: убираем протокол, если есть
            String host = remoteUrl.replaceAll("^https?://", "");
            Configuration.remote = "https://" + login + ":" + password + "@" + host;

            System.out.println("🌐 Remote: " + Configuration.remote);
        } else {
            // Локальный запуск — remote не устанавливаем
            Configuration.remote = null;
            System.out.println("🏠 Local browser");
        }
    }

    @BeforeEach
    void setUp() {
        SelenideSetup.applyConfig();
        Configuration.timeout = 15000;
        new CookieBannerComponent().closeIfPresent();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    void addAttachments() {
        try {

            if (WebDriverRunner.hasWebDriverStarted()) {
                Attach.screenshotAs("Last screenshot");
                Attach.pageSource();
                Attach.browserConsoleLogs();
                Attach.addVideo();
            }

            closeWebDriver();


        } catch (Exception e) {
            System.err.println("⚠️ Ошибка при сборе аттачментов: " + e.getMessage());
        }
    }
}