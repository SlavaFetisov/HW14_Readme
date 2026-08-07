package pages;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TuningcentrPage {

    private final SelenideElement katalog = $("[dropdown-toggle menu-catalog]");
    private final SelenideElement procheeMenu = $("[data-menu-submenu-hook='"
            + testdata.TestData.MENU_PROCHEE + "']");
    private final SelenideElement infoMenu = $("[data-menu-submenu-hook='"
            + testdata.TestData.MENU_INFO + "']");
    private final ElementsCollection dropdownItems = $$(".t-menusub__menu_show .t-menusub__list-item");
    private final ElementsCollection dropdownLinks = $$(".t-menusub__menu_show .t-menusub__link-item");
    private final ElementsCollection products = $$(".js-product");
    private final SelenideElement productSku = $(".t-store__prod-popup__sku");
    private final SelenideElement productPrice = $(".t-store__prod-popup__price-value");

    public TuningcentrPage openPage() {
        open(testdata.TestData.BASE_URL);
        return this;
    }

    public TuningcentrPage openCatsPage() {
        open(testdata.TestData.CATS_URL);
        return this;
    }

    public TuningcentrPage hoverkatalog() {
        katalog.shouldBe(visible).hover();
        return this;
    }

    public TuningcentrPage hoverProcheeMenu() {
        $("body").click();
        sleep(300);
        procheeMenu.shouldBe(visible).hover();
        sleep(500);
        return this;
    }

    public TuningcentrPage hoverInfoMenu() {
        infoMenu.shouldBe(visible).hover();
        return this;
    }

    public TuningcentrPage checkDropdownSize(int expectedSize) {
        dropdownItems.shouldHave(size(expectedSize));
        return this;
    }

    public TuningcentrPage checkDropdownTexts(String... expectedTexts) {
        dropdownLinks.shouldHave(texts(expectedTexts));
        return this;
    }

    public TuningcentrPage clickProductByName(String productName) {
        products.findBy(text(productName)).click();
        return this;
    }

    public TuningcentrPage checkProductSku(String expectedSku) {
        productSku.shouldHave(text(expectedSku));
        return this;
    }

    public TuningcentrPage checkProductPrice(String expectedPrice) {
        productPrice.shouldHave(text(expectedPrice));
        return this;
    }
}