# wildberries_tech_task

**Запуск**
```
mvn clean test
```

**Настройки запуска**

Чтобы тест заработал, надо:

- на сайте wildberries добавить в корзину товар
- в файле config.properties установить значение BasketUID для вашей корзины (из cookies запроса)
- в файле config.properties установить значение itemId для товара в Вашей корзине (из параметров запроса)
