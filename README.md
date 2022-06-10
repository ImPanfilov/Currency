# Сurrency Comparison Application

Для локального запуска:
gradlew bootrun

##
Получить результат:
http://localhost:8080/compare/{КодВалюты}

Например:
http://localhost:8080/compare/RUB
http://localhost:8080/compare/BTC

##
Сборка и запуск Docker контейнера:

gradlew build

docker build . --tag currency

docker run -p 8080:8080 currency

