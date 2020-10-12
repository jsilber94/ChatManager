import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Message {
    String message;
    String user;
    LocalDateTime dateTime;
    private LocalDate date;
    private LocalTime time;

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String toString() {
        return user + ": " + message;
    }


}
