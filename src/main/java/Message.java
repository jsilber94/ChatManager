import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Message {
    String message;
    private LocalDate date;
    private LocalTime time;

    LocalDateTime dateTime;

    public Message(String message) {
        this.message = message;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public LocalDate getDate(){
        return this.date;
    }
    public LocalTime getTime(){
        return this.time;
    }
    public String toString() {
        return message + date.toString() + time.toString();
    }


}
