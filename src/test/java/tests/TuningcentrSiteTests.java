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
    @DisplayName("Проверка каталога услуг")
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

    @Test
    @DisplayName("Filling required form fields")
    void requiredFieldsOnlyTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Filling required form fields", () -> {
            practiceFormPage.bannerClose()
                    .checkSubtitle(testData.subtitleText)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .selectGender(testData.genderTextValue)
                    .typeNumber(testData.userNumber)
                    .setDateOfBirth(testData.dateDay, testData.dateMonth, testData.dateYear)
                    .submitForm();
        });
        step("Check results", () -> {
            resultModalComponent.checkResultModalAppearance()
                    .checkResultModalTitle(testData.successSubmitText);
        });
    }

    @Test
    @DisplayName("Empty form submit")
    void unsuccessfulSubmitTest(){
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Submit empty form", () -> {
            practiceFormPage.bannerClose()
                    .submitForm()
                    .checkFormErrorText(testData.formError);
        });
        step("Check error text", () -> {
            practiceFormPage.checkFormErrorText(testData.formError);
        });
    }

    @Test
    @DisplayName("Incorrect phone number submit")
    void incorrectNumberTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Incorrect phone number submit", () -> {
            practiceFormPage.bannerClose()
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .typeNumber(testData.incorrectUserNumber)
                    .submitForm();
        });
        step("Check error text", () -> {
            practiceFormPage.checkFormErrorText(testData.formError);
        });
    }

    @Test
    @DisplayName("Submit form with empty first name")
    void submitWithoutNameTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Submit form with empty first name", () -> {
            practiceFormPage.bannerClose()
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .typeNumber(testData.userNumber)
                    .submitForm();
        });
        step("Check error text", () -> {
            practiceFormPage.checkFormErrorText(testData.formError);
        });
    }
}