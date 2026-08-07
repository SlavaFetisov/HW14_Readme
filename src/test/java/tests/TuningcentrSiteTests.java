package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testdata.TestData;
import pages.TuningcentrPage;

import static io.qameta.allure.Allure.step;

@Story("Website https://tuningcentr.ru/")
public class TuningcentrSiteTests extends TestBase {

    TuningcentrPage tuningcentrPage = new TuningcentrPage();

    @Test
    @DisplayName("Проверка меню")
    void shouldCheckDropdownMenu() {
        step("Открыть главную страницу", () ->
                tuningcentrPage.openPage()
        );

        step("Навести на каталог", () ->
                tuningcentrPage.hoverkatalog()
        );

        step("Проверить количество пунктов", () ->
                tuningcentrPage.checkDropdownSize(TestData.KATALOG_SIZE)
        );

        step("Проверить названия пунктов", () ->
                tuningcentrPage.checkDropdownTexts(TestData.KATALOG_ITEMS)
        );
    }


}