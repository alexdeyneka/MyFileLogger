package loggers;

import beans.Event;

import java.io.IOException;
import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private final List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for(EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }
}
