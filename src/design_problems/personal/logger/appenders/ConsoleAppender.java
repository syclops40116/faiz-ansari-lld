package design_problems.personal.logger.appenders;

import design_problems.personal.logger.formatter.LogFormatter;
import design_problems.personal.logger.model.LogMessage;

public class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}
