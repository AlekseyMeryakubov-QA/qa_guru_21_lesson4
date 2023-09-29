package ru.meryakubov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.meryakubov.pages.MainPage;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

@Tag("main")
@Feature("Проверка главной страницы")
@Owner("Overloque")
public class MainTests extends BaseTest {
    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка появления списка подсказок в поисковой строке по сгенерированному значению")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "SBER", url = "https://www.sberbank.ru/")
    void checkSearchTips() {
        step("Открытие главной страницы сайта", () ->
                mainPage.openPage());
        step("Открытие меню поиска");
                mainPage.openSearchMenu();
        step("Заполнение поисковой строки сгенерированным значением", () ->
                mainPage.typeText(variables.searchInput));
        step("Проверка появившегося списка подсказок по запросу", () ->
                mainPage.checkTipsExist());
    }

    @Test
    @DisplayName("Проверка элементов списка из меню на главной странице")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "SBER", url = "https://www.sberbank.ru/")
    void checkEmptyField() {
        step("Открытие главной страницы сайта", () ->
                mainPage.openPage());
        step("Проверка существования элементов из списка", () ->
                mainPage.checkMenuPanelExist(variables.menuPanel));
    }
}