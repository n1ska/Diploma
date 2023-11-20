# Дипломная работа для netology.ru

## Описание:
Корневая папка репозитория содержит следующие директории: 
- docs - документация по выполненой работе
1. ![Plan.md]([https://github.com/n1ska/diploma/blob/main/docs/Plan.md])
2. ![Report.md](https://github.com/n1ska/diploma/blob/main/docs/Report.md)
3. ![Summary.md](https://github.com/n1ska/diploma/blob/main/docs/Summary.md)
- artifacts - исходные приложения для тестирования.
- src - исходники тестов
- reports - сгенерированный отчеты пройденных тестов
    
## Для выполнения тестов:

### Необходимо установить на компьютер:
  1. Docker Desktop
  2. Openjdk 11.0.20

### Выполнить следующие шаги:
  1. Средствами коммандной строки, необходимо перезаписать файл application.properties файлом application-<db_type>.properties в зависимости от теста приложения на MySql или Postgres базы данными:
  Для использования под Windows можно использовать следующий скрипт:
  xcopy /f /y artifacts\application-mysql.properties artifacts\application.properties
  2. Запустить Docker Desktop приложение на компьютере
  3. Средствами коммандной строки выполнить следующую комманду
     docker compose up -d
  4. Открыть консоль в директории artifacts
     cd artifacts
  5. Запустить приложение через консоль выполнив следующую комманду:
    java -jar aqa-shop.jar
  6. Перейти в корневую директорию репозитория и запустить тест
    gradlew test --rerun --info -Dselenide.headless=true
  7. Открыть web страницу index.html из build\reports\tests\test для проверки результатов
     ![image](https://github.com/n1ska/diploma/assets/130662674/10fd2bcf-62cd-40cb-b7da-20f5c4b81449)
   
! Если используется Windows можно использовать автоматизированные скрипты запуска теста и наастройки окружения
test-mysql.bat для запуска тестирования на Mysql БД и test-postgres.bat для запуска тестирование c Postgres БД

  Проект содержит 18 тестов:
  
- 16 UI тестов:
  1. 8 UI (buyUI_*) тестов для тестирования валидаций ввода данных банковской карты после нажатия кнопки "Купить"
  2. 8 UI (buyCreditUI_*) тестов для тестирования валидаций ввода данных банковской карты после нажатия кнопки "Купить в кредит"
 - 2 БД теста 
  1. buyCredit_CheckExistingApprovedRecordsInDatabase - проверка наличия записи APPROVED в credit_request_entity таблице после добавляения в UI через кнопку "Купить".
 2. buy_CheckExistingApprovedRecordsInDatabase - проверка наличия записи APPROVED в payment_entity таблице после добавляения в UI через кнопку "Купить в кредит".

! Тесты используют application.properties для настройки соединения с БД.
