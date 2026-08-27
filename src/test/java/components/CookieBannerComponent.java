package components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static testdata.TestData.COOKIE_ACCEPT_TEXT;

public class CookieBannerComponent {
    private final SelenideElement acceptButton = $(byText(COOKIE_ACCEPT_TEXT));

    @Step("Закрыть баннер cookie (если присутствует)")
    public void closeIfPresent() {
        try {
            acceptButton.shouldBe(visible, java.time.Duration.ofSeconds(2)).click();
        } catch (Throwable ignored) {
        }
    }
}