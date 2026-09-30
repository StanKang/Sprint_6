# Sprint_6 — Автотесты для Яндекс.Самокат

Автотесты написаны на Java 11 с использованием Selenium 4 и JUnit 5.

## Запуск тестов

```bash
mvn test
```

## Структура проекта

```
src/test/java/ru/yandex/samokat/
├── pageobjects/
│   ├── MainPage.java
│   ├── OrderPage.java
│   ├── OrderConfirmPage.java
│   └── TrackPage.java
└── tests/
    ├── BaseTest.java
    ├── FAQTest.java
    ├── LogoTest.java
    ├── OrderTest.java
    └── TrackTest.java
```

## Тестовые сценарии

- **FAQTest** — раздел «Вопросы о важном» (8 тестов)
- **OrderTest** — позитивный флоу заказа + валидация полей (3 теста)
- **LogoTest** — логотипы Самоката и Яндекса (2 теста)
- **TrackTest** — несуществующий номер заказа (1 тест)
