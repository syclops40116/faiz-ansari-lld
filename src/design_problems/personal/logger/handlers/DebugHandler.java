package design_problems.personal.logger.handlers;

import design_problems.personal.logger.enums.LogLevel;

public class DebugHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }
}
