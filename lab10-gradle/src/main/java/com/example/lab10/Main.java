package com.example.lab10;

import com.example.stringutils.StringProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("начало выполнения приложения");

        // читаем сгенерированный грейдлом паспорт
        readBuildPassport();

        System.out.print("введи какую-нибудь строку: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        logger.debug("введенная строка: {}", userInput);

        // юзаем наш сабмодуль с апач коммонс
        String processed = StringProcessor.process(userInput);

        logger.info("результат обработки: {}", processed);
        
        logger.info("успешное завершение работы");
    }

    private static void readBuildPassport() {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("build-passport.properties")) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                logger.info("=== инфа о сборке ===");
                logger.info("номер: {}", props.getProperty("build.number"));
                logger.info("кто собрал: {}", props.getProperty("build.user"));
                logger.info("ось: {}", props.getProperty("build.os"));
                logger.info("джава: {}", props.getProperty("build.java"));
                logger.info("сообщение: {}", props.getProperty("build.message"));
                logger.info("коммит: {}", props.getProperty("build.commit"));
                logger.info("=====================");
            } else {
                logger.warn("файл build-passport.properties не найден");
            }
        } catch (Exception e) {
            logger.error("ошибка при чтении паспорта сборки", e);
        }
    }
}