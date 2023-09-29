package ru.meryakubov.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement

            searchInput = $(".categories-search__input"),

            autocompletePopover = $(".categories-search__suggest");

    private final ElementsCollection menuPanel = $$(".kitt-top-menu");

    public MainPage openPage() {
        open("/ru/search");
        return this;
    }
    public MainPage openSearchMenu() {
        searchInput.hover().click();

        return this;
    }

    public MainPage typeText(String value) {
        searchInput.setValue(value);

        return this;
    }


    public MainPage checkMenuPanelExist(List<String> menu) {
        for (String menuElement : menu) {
            menuPanel.findBy(text(menuElement)).should(exist);
        }

        return this;
    }

    public MainPage checkTipsExist() {
        autocompletePopover.shouldBe(visible);

        return this;
    }
}