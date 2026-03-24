package design_problems.personal.logger;

import design_problems.personal.logger.appenders.LogAppender;
import design_problems.personal.logger.enums.LogLevel;
import design_problems.personal.logger.handlers.*;

public class LogHandlerConfiguration {

    private static final LogHandler debug = new DebugHandler();
    private static final LogHandler info = new InfoHandler();
    private static final LogHandler warn = new WarnHandler();
    private static final LogHandler error = new ErrorHandler();

    public static LogHandler build(){
        debug.setNext(info);
        info.setNext(warn);
        warn.setNext(error);

        return debug;
    }

    public static void addAppenderForLevel(LogLevel level, LogAppender appender) {
        switch (level) {
            case DEBUG -> debug.subscribe(appender);
            case INFO  -> info.subscribe(appender);
            case WARN  -> warn.subscribe(appender);
            case ERROR -> error.subscribe(appender);
        }
    }
}

