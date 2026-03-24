package design_problems.personal.logger.handlers;

import design_problems.personal.logger.enums.LogLevel;

public class InfoHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}
