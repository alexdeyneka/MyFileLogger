import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.eventLogger = logger;
    }

    private void logEvent(Event event, String s) {
        event.setMsg(s);
        try {
            eventLogger.logEvent(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext cac = new ClassPathXmlApplicationContext(
                "spring.xml");

        App app = (App) cac.getBean("app");
        app.logEvent((Event)cac.getBean("event"), "some message 1");
        sleep(1000);
        app.logEvent((Event)cac.getBean("event"), "some message 2");
        cac.close();
    }
}
