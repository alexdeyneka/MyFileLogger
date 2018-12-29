import java.util.Date;
import java.util.Random;

public class Event {
    private int id;
    private String msg;
    private Date date;

    public Event(Date date) {
        this.id = new Random().nextInt(10);
        this.date = date;
    }

    void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
