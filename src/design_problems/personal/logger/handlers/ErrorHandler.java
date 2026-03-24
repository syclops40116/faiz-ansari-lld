package design_problems.personal.logger.handlers;

import design_problems.personal.logger.enums.LogLevel;

public class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }
}
