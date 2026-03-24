package design_problems.personal.logger.handlers;

import design_problems.personal.logger.appenders.LogAppender;
import design_problems.personal.logger.enums.LogLevel;
import design_problems.personal.logger.model.LogMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {
    protected LogHandler next;
    protected final List<LogAppender> logAppenders = new CopyOnWriteArrayList<>();

    public void subscribe(LogAppender observer) {
        logAppenders.add(observer);
    }

    public void notifyObservers(LogMessage message) {
        for (LogAppender appender : logAppenders) {
            appender.append(message);
        }
    }

    public void handle(LogMessage message) {
        if (canHandle(message.getLevel())) {
            notifyObservers(message);
        }
        else if (next != null) {
            next.handle(message);
        }
    }

    public void setNext(LogHandler next) {
        this.next = next;
    }

    protected abstract boolean canHandle(LogLevel level);
}
