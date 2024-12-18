<h1 align="center">Task Management System</h1>

Система обеспечивает создание, редактирование, удаление и просмотр задач.
Каждая задача содержит заголовок, описание, статус, приоритет, автора и исполнителя.

Создать задачу может только аутентифицированный пользователь с ролью admin
Назначать исполнителя при создании задачи, а также в дальнейшем изменять исполнителя может только автор

Обновление задачи возможно только автору.
Изменение статуса задачи доступно автору или исполнителю задачи.

Удаление задачи доступно только автору.

Получить задачу по id или список задач с использованием фильтров может любой аутентифицированный пользователь.

Для получения всех задач, созданных пользователем, необходимо заполнить параметр authorId.
Для получения всех задач, где пользователь указан исполнителем, необходимо заполнить параметр executorId.
Предусмотрена возможность добавления фильтров по полям status и priority. При отсутствии всех параметров будут получены все задачи
Поиск задач по нескольким необязательным условиям реализован с использованием паттерна спецификация.

Добавление комментария к любой существующей задаче доступно для всех аутентифицированных пользователей. 

Безопасность веб-приложения обеспечена использованием Spring Security и JWT токена. Время жизни токена 15 миинут
В качестве базы данных использован PostgreSQL. Для управления миграциями базы данных использована Flyway.

Для работы с задачами создано 2 пользователя. Логины и пароли предоставлены ниже :

|       логин       |  пароль   |
|:-----------------:|:---------:|
|  polina@mail.ru   |    123    |
|   Vova@mail.ru    |    123    |

Документация API создана с использованием swagger и представлена по ссылке: http://localhost:8080/swagger-ui/index.html#/

Для запуска сервиса необходимо запустить файл docker-compose.yml из графического интерфейса IDE