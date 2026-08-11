package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OpenWeatherPage {

    private final SelenideElement inputSearch = $("input[placeholder='Search City']");
    private final SelenideElement selectCity = $("div[class='text-base font-medium text-white']");
    private final SelenideElement verifyResults = $("span[class='text-base font-normal text-white whitespace-nowrap max-lg:text-sm']");


    private final SelenideElement procheeMenu = $("[data-menu-submenu-hook='"
            + testdata.TestData.MENU_PROCHEE + "']");
    private final SelenideElement infoMenu = $("[data-menu-submenu-hook='"
            + testdata.TestData.MENU_INFO + "']");
    private final ElementsCollection dropdownItems = $$(".dropdown-submenu a[tabindex='-1'].parent");
    private final ElementsCollection dropdownLinks = $$(".t-menusub__menu_show .t-menusub__link-item");
    private final ElementsCollection products = $$(".js-product");
    private final SelenideElement productSku = $(".t-store__prod-popup__sku");
    private final SelenideElement productPrice = $(".t-store__prod-popup__price-value");

    public OpenWeatherPage openPage() {
        open(testdata.TestData.BASE_URL);
        return this;
    }

    public OpenWeatherPage setInputValue(String value) {
        inputSearch.setValue(value);

        return this;
    }
    public OpenWeatherPage clickCity() {
            selectCity.click();
        return this;
    }

    public OpenWeatherPage verifyResults(String value) {
        verifyResults.shouldHave(text(value));
        return this;
    }

//    public OpenWeatherPage hoverkatalog() {
//        katalog.shouldBe(visible).hover();
//        return this;
//    }
//
//    public OpenWeatherPage hoverProcheeMenu() {
//        $("body").click();
//        sleep(300);
//        procheeMenu.shouldBe(visible).hover();
//        sleep(500);
//        return this;
//    }
//
//    public OpenWeatherPage hoverInfoMenu() {
//        infoMenu.shouldBe(visible).hover();
//        return this;
//    }
//
//    public OpenWeatherPage checkDropdownSize(int expectedSize) {
//        dropdownItems.shouldHave(size(expectedSize));
//        return this;
//    }
//
//    public OpenWeatherPage checkDropdownTexts(String... expectedTexts) {
//        dropdownLinks.shouldHave(texts(expectedTexts));
//        return this;
//    }
//
//    public OpenWeatherPage clickProductByName(String productName) {
//        products.findBy(text(productName)).click();
//        return this;
//    }
//
//    public OpenWeatherPage checkProductSku(String expectedSku) {
//        productSku.shouldHave(text(expectedSku));
//        return this;
//    }
//
//    public OpenWeatherPage checkProductPrice(String expectedPrice) {
//        productPrice.shouldHave(text(expectedPrice));
//        return this;
//    }
}