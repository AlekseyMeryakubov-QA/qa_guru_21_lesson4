package ru.meryakubov.utils;

import java.util.List;

public class TestDataVariables {
    private final TestDataMethods testDataMethods = new TestDataMethods();
    public String
            searchInput = testDataMethods.getRandomCardType(),
            goalsInput = testDataMethods.getRandomCreditGoal();

    public List<String>
            menuPanel = List.of("SberPay", "СберПрайм+",
            "Кредиты", "Ипотека", "Карты", "Вклады", "Премиум",
            "Инвестиции", "Платежи", "Переводы","Страхование","Поддержка"),
            steps = List.of("Оставьте заявку на кредит, ответим сразу, в день обращения",
                    "Получите деньги на карту Тинькофф. Если ее нет, доставим бесплатно, куда удобно",
                    "Тратьте деньги с карты или снимайте наличные без комиссии");
}
