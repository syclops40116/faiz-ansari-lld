package design_problems.personal.logger;

import design_problems.personal.logger.appenders.ConsoleAppender;
import design_problems.personal.logger.enums.LogLevel;
import design_problems.personal.logger.formatter.PlainTextFormatter;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.INFO,
                new ConsoleAppender(new PlainTextFormatter())
        );

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.ERROR,
                new ConsoleAppender(new PlainTextFormatter())
        );

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.ERROR,
                new ConsoleAppender(new PlainTextFormatter())
        );

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                logger.info("This is some key information " + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                logger.error("Oh no! there's an error " + i); // CONSOLE + FILE
            }
        }).start();
    }
}
