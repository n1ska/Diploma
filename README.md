Дипломная работа для netology.ru
Описание:
  Корневая папка репозитория содержит два script bat файла для автоматического запуска docker контейнеров (MySQL, Posgtres, payment-gate) и aqa-shop.jar приложения и процесса тестирования.
  Скрипт выполняет следующие шаги:
    1. Перезаписывает application.properties файл следующим файлом: application-mysql.properties, application-postgres.properties в зависимости от используемой базы данных.
    2. Запускаються Docker-ом контейнеры из файла docker-compose.yml
    3. Запускаеться aqa-shop web приложение
    4. После 15 секундного таймаута, запускаеться gradlew test
  Проект содержит 18 тестов: 
    - 16 UI тестов:
        1. 8 UI (buyUI_*) тестов для тестирования валидаций ввода данных банковской карты после нажатия кнопки "Купить"
        2. 8 UI (buyCreditUI_*) тестов для тестирования валидаций ввода данных банковской карты после нажатия кнопки "Купить в кредит"
    - 2 БД теста 
      1. buyCredit_CheckExistingApprovedRecordsInDatabase - проверка наличия записи APPROVED в credit_request_entity таблице после добавляения в UI через кнопку "Купить".
      2. buy_CheckExistingApprovedRecordsInDatabase - проверка наличия записи APPROVED в payment_entity таблице после добавляения в UI через кнопку "Купить в кредит".
    ! Тесты используют application.properties для настройки соединения с БД.
      
Для запуска необходимо:
Необходимо установить на компьютер:
  1. Docker Desktop
  2. Openjdk 11.0.20
Шаги для запуска:
  1. Запустить Docker Desktop приложение
  2. Создать test_diploma папку в корне диска C:\
  3. Открыть консоль в test_diploma папке
  4. Выполнить клонирование репозитория git clone https://github.com/n1ska/diploma.git
  5. Перейти в папку diploma с помощью команды CD в консоле
  6. Запустить скрипт test-mysql.bat для запуска тестирования на Mysql БД
  7. Открыть web страницу index.html из build\reports\tests\test для проверки результатов для Mysql
     ![image](https://github.com/n1ska/diploma/assets/130662674/10fd2bcf-62cd-40cb-b7da-20f5c4b81449)
  8. Закрыть автоматически запущеное aqa-shop.jar приложение предыдущим скриптом
     ![image](https://github.com/n1ska/diploma/assets/130662674/70dc55e0-47de-45ac-b14e-253ae146752e)
  9. Запустить скрипт test-postgres.bat для запуска тестирование c Postgres БД и повторить шаги 7-8
