package design_problems.personal.logger.model;

import design_problems.personal.logger.enums.LogLevel;


public class LogMessage {
    private LogLevel level;
    private String message;
    private long timestamp;

    public LogMessage(LogLevel level, String message, long timestamp) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public LogLevel getLevel() {
        return level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
