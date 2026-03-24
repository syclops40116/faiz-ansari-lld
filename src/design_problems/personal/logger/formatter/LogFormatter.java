package design_problems.personal.logger.formatter;

import design_problems.personal.logger.model.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}