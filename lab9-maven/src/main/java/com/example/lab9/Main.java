package com.example.lab9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Начало выполнения приложения (Maven проект).");

        User user = new User("Иван Иванов", 20, "ivanov@example.com");
        logger.debug("Создан тестовый объект пользователя: {}", user.getName());

        JsonSerializer serializer = new JsonSerializer();

        String jsonResult = serializer.serialize(user);
        logger.info("Результат сериализации (JSON): {}", jsonResult);

        if (jsonResult != null) {
            User deserializedUser = serializer.deserialize(jsonResult, User.class);
            if (deserializedUser != null) {
                logger.info("Объект успешно десериализован. Имя: {}, Почта: {}",
                        deserializedUser.getName(), deserializedUser.getEmail());
            } else {
                logger.error("Десериализация вернула null.");
            }
        }

        logger.info("Приложение успешно завершило работу.");
    }
}