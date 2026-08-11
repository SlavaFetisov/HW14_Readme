package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testdata.TestData;

import static io.qameta.allure.Allure.step;

@Story("Website https://openweathermap.org/")
public class OpenWeatherSiteTests extends TestBase {

   

    @Test
    @DisplayName("Проверка поиска")
    void searchResultsMenu() {
        step("Открыть главную страницу", () ->
                openWeatherPage.openPage()
        );

        step("Ввести в поиске город", () ->
                openWeatherPage.setInputValue("Tomsk")
        );
        step("Из выпадающего списка выбрать город", () ->
                openWeatherPage.clickCity()
        );

        step("Проверить результат изменения заголовка", () ->
                openWeatherPage.verifyResults("Tomsk, RU")
        );

    }


}