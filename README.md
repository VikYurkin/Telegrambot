# Telegrambot
Telegram bot to view new articles
Для запуска через docker compose:  
1) Создать файл  docker-compose.yml:


```sh
version: '3.7'

services:
  tb:
    depends_on:
      - postgres
    image: vyurkin/telegrambot_to_view_new_articles
    environment:
      BOT_DB_USERNAME: Name
      BOT_DB_PASSWORD: Password
      BOT_URL: jdbc:postgresql://postgres:5432/tbDB
    restart: always

  postgres:
    image: postgres:15.2
    restart: always
    environment:
      POSTGRES_PASSWORD: Password
      POSTGRES_USER: Name
      POSTGRES_DB: tbDB
    ports:
      - 5433:5432

```

2) Запустить сборку образа командой:

 docker compose -p "project-tb" -f "docker-compose.yml" up -d.
 
если образы vyurkin/telegrambot_to_view_new_articles и postgres:15.2 отсутствуют, они будут скачаны с DockerHub.

Функционал:
После запуска контейнера в телеграмме найти бота по имени [TEST] MyBot.  
Бот позволяет подписываться на группы статей с сайта javarush и получать непрочитанные статьи.  
Для начала работы ввести команду "/start", по команде "/help" можно получить список допустимых команд.  
По команде "/addgroupsub" список допустимых групп, чтобы подписаться ввести команду "/addGroupSub id", где id номер группы (Например: "/addGroupSub 16"), аналогично по команде "/deletegroupsub  id" можно отписаться от группы, "/listgroupsub" посмотреть все группы.  
Раз в 15 мин происходит проверка по всем подписанных группам для каждого пользователя и высылаются новые непрочитанные статьи с момента начала подписки.  
По команде "/stop" работа с ботом оканчивается.
