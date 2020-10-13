import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {
    String message;
    String user;
    private LocalDate date;
    private String time;

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
        this.date = LocalDate.now();
        this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        return user + ": " + message;
    }


}
