package tests.utils;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import tests.config.WebConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelenideSetup {

    public static void applyConfig() {
        String env = System.getProperty("env", "local");
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.browser = config.getBrowser();

        if (config.getBrowserVersion() != null && !config.getBrowserVersion().isEmpty()) {
            Configuration.browserVersion = config.getBrowserVersion();
        }

        Configuration.browserSize = config.getBrowserSize();
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.headless = config.isHeadless();

        String remoteUrl = config.getRemoteUrl();
        Configuration.remote = (remoteUrl != null && !remoteUrl.isEmpty()) ? remoteUrl : null;

        if (config.isHeadless()) {
            Map<String, Object> chromeOptions = new HashMap<>();
            List<String> args = new ArrayList<>();

            // Базовые флаги для Docker/Selenoid
            args.add("--no-sandbox");
            args.add("--disable-dev-shm-usage");

            // Флаги, которые лечат черный экран при записи видео в headless
            args.add("--disable-gpu");
            args.add("--window-size=1920,1080"); // ВАЖНО: именно запятая, а не 'x'
            args.add("--force-color-profile=srgb"); // Помогает корректному рендерингу
            args.add("--disable-software-rasterizer");

            chromeOptions.put("args", args);
            Configuration.browserCapabilities.setCapability("goog:chromeOptions", chromeOptions);
        }

        System.out.println("\n=========================================");
        System.out.println("🚀 ЗАПУСК ТЕСТА (env = " + env + ")");
        System.out.println("🌐 Браузер: " + Configuration.browser);
        System.out.println("🔗 Remote URL: " + (Configuration.remote == null ? "LOCAL (null)" : Configuration.remote));
        System.out.println("=========================================\n");

    }
}