xcopy /f /y artifacts\application-postgres.properties artifacts\application.properties
docker compose up -d
set current=%CD%
cd artifacts
start java -jar aqa-shop.jar
timeout 15
cd ..
gradlew test --rerun --info -Dselenide.headless=true
