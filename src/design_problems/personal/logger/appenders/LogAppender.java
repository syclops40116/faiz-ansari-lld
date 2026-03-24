package design_problems.personal.logger.appenders;

import design_problems.personal.logger.model.LogMessage;

public interface LogAppender {
    void append(LogMessage message);

    // can have other appends like file appender etc;
}
