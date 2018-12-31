import beans.Client;
import beans.Event;
import loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger logger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = logger;
        this.loggers = loggers;
    }

    private void logEvent(EventType eventType, Event event, String s) throws IOException {
        String message = s.replaceAll(client.getId(), client.getName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext cac = new ClassPathXmlApplicationContext(
                "spring.xml");

        App app = (App) cac.getBean("app");

        Event event = cac.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = cac.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = cac.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        cac.close();
    }
}
