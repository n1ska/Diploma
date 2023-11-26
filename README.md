# Дипломная работа для netology.ru

## Описание:
Корневая папка репозитория содержит следующие директории: 
- docs - документация по выполненой работе
1. [Plan.md](docs/Plan.md)
2. [Report.md](docs/Report.md)
3. [Summary.md](docs/Summary.md)
- artifacts - исходные приложения для тестирования.
- src - исходники тестов
- reports - сгенерированный отчеты пройденных тестов
    
## Для выполнения тестов:

### Необходимо установить на компьютер:
  1. Docker Desktop
  2. Openjdk 11.0.20

### Выполнить следующие шаги:
  1. Запустить Docker Desktop приложение на компьютере
  2. Средствами коммандной строки выполнить следующую комманду
     docker compose up -d
  3. Дождатся запуска всех контейнеров
    - mysql db - база данных mysql для aqa-shop-mysql
    - postgres db - база данных postgres для aqa-shop-postgres
    - payment-gate - эмулятор банковского сервиса
    - aqa-shop-mysql - web приложения работающее с mysql бд (http://localhost:8081)
    - aqa-shop-postgres - web приложения работающее с postgress бд (http://localhost:8082)
  4. Запустить таск прохождение тестов
    ./gradlew test --rerun --info
  5. Запустить таск генерации отчета 
    ./gradlew allureServe


