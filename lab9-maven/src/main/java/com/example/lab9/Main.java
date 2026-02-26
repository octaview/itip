package com.example.lab9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("начало выполнения приложения");

        User user = new User("кирюха octaview", 67, "octaview@gmail.com");
        logger.debug("создан тестовый объект пользователя: {}", user.getName());

        JsonSerializer serializer = new JsonSerializer();

        String jsonResult = serializer.serialize(user);
        logger.info("результат сериализации жсон: {}", jsonResult);

        if (jsonResult != null) {
            User deserializedUser = serializer.deserialize(jsonResult, User.class);
            if (deserializedUser != null) {
                logger.info("юзер успешно десериализован - имя: {}, почта: {}",
                        deserializedUser.getName(), deserializedUser.getEmail());
            } else {
                logger.error("десериализация вернула null");
            }
        }

        logger.info("успешное завершение работы");
    }
}