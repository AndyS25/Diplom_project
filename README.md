# Дипломный проект профессии «Тестировщик»
Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Документация проекта
1. [План автоматизации](documents/Plan.md)
1. [Отчётные документы по итогам тестирования](documents/Report.md)
1. [Отчётные документы по итогам автоматизации](documents/Summary.md)
   </a>

## Запуск приложения — веб-сервиса, автотестов и создание отчёта системы репортинга
### Подготовительный этап и необходимое ПО:
1. Установить IntelliJ IDEA;
1. Установить Docker Desktop;
1. Склонировать удаленный репозиторий с Github на свой ПК [ссылка на проект](https://github.com/AndyS25/Diplom_project).

### Подключение к PostgreSQL

1. Запустить Docker Desktop
1. Запустить IntelliJ IDEA и открыть склонированный проект
1. Проверить и поправить при необходимости настройки в файлах в корне проекта:

в файле [application.properties](https://github.com/AndyS25/Diplom_project/blob/main/application.properties) должны быть следующие данные:
```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
    #spring.datasource.url=jdbc:mysql://localhost:3306/app
spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```
в файле [build.gradle](https://github.com/AndyS25/Diplom_project/blob/993fe71fdcc0c0b64063637cbd33de8a99ffa683/build.gradle#L41) в блоке test
```
test {
    useJUnitPlatform()
    systemProperty 'selenide.headless', System.getProperty('selenide.headless')
    //systemProperty 'db.url', System.getProperty('db.url', "jdbc:mysql://localhost:3306/app")
    systemProperty 'db.url', System.getProperty('db.url', "jdbc:postgresql://localhost:5432/app")
    systemProperty 'sut.url', System.getProperty('sut.url', 'http://localhost:8080')
    systemProperty 'chromeoptions.prefs', System.getProperty('chromeoptions.prefs', "profile.password_manager_leak_detection=false")
}
```
4. В терминале открытого проекта запустить контейнеры с помощью команды:
   `docker compose up`
1. Открыть второе окно терминала и запустить приложение с помощью команды:

   `java -jar .\artifacts\aqa-shop\aqa-shop.jar`
1. Открыть третье окно терминала и запустить прогон автотестов с помощью команды:

   `.\gradlew clean test`
1. После окончания прогона автотестов создать отчёт Allure и открыть его в браузере с помощью команды в терминале:

   `.\gradlew allureServe`
1. Для закрытия отчёта выполнить в терминале следующие действия:

   нажать **CTRL+C**, ввести **y** и нажать  **Enter**
1. Перейти во второе окно терминала, где запущено приложение и остановить его работу:

   **CTRL+C**
1. Перейти в первое окно терминала, где запущены контейнеры и остановить их работу и удалить с помощью команды:

   `docker compose down`
   </a>

### Подключение SUT к MySQL

1. Запустить Docker Desktop
1. Запустить IntelliJ IDEA и открыть склонированный проект
1. Проверить и поправить при необходимости настройки в файлах в корне проекта:

в файле [application.properties](https://github.com/AndyS25/Diplom_project/blob/main/application.properties) должны быть следующие данные:
```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
spring.datasource.url=jdbc:mysql://localhost:3306/app
    #spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```
в файле [build.gradle](https://github.com/AndyS25/Diplom_project/blob/993fe71fdcc0c0b64063637cbd33de8a99ffa683/build.gradle#L41) в блоке test
```
test {
    useJUnitPlatform()
    systemProperty 'selenide.headless', System.getProperty('selenide.headless')
    systemProperty 'db.url', System.getProperty('db.url', "jdbc:mysql://localhost:3306/app")
    //systemProperty 'db.url', System.getProperty('db.url', "jdbc:postgresql://localhost:5432/app")
    systemProperty 'sut.url', System.getProperty('sut.url', 'http://localhost:8080')
    systemProperty 'chromeoptions.prefs', System.getProperty('chromeoptions.prefs', "profile.password_manager_leak_detection=false")
}
```
4. В терминале открытого проекта запустить контейнеры с помощью команды:
   `docker compose up`
1. Открыть второе окно терминала и запустить приложение с помощью команды:

   `java -jar .\artifacts\aqa-shop\aqa-shop.jar`
1. Открыть третье окно терминала и запустить прогон автотестов с помощью команды:

   `.\gradlew clean test`
1. После окончания прогона автотестов создать отчёт Allure и открыть его в браузере с помощью команды в терминале:

   `.\gradlew allureServe`
1. Для закрытия отчёта выполнить в терминале следующие действия:

   нажать **CTRL+C**, ввести **y** и нажать  **Enter**
1. Перейти во второе окно терминала, где запущено приложение и остановить его работу:

   **CTRL+C**
1. Перейти в первое окно терминала, где запущены контейнеры и остановить их работу и удалить с помощью команды:

   `docker compose down`
   </a>