package ru.meryakubov;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @Test
    @Feature("Issue в репозиториии")
    @Story("Создание Issue")
    @Owner("merykubovar")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание issue для авторизованного пользователя")
    public void testStaticLabels() {
    }

    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание issue для авторизованного пользователя")
        );
        Allure.feature("Issue в репозиториии");
        Allure.story("Создание Issue");
        Allure.label("owner", "merykubovar");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("Testing","https://testing.github.com");
    }
}
