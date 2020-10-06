import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChatManager {


    private final List<String> users = new ArrayList<>();
    private final List<Message> messages = new ArrayList<>();

    private final String defaultFormat = "plain-text";

    public void addUser(String user) {
        users.add(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String getAllMessagesWithinRange(String format) {
        return formatMessage(messages, format);
    }

    public String getAllMessagesWithinRange(String from, String to) {
        return getAllMessagesWithinRange(from, to, defaultFormat);
    }

    public String getAllMessagesWithinRange(String from, String to, String format) {
        List<Message> messagesToSend = new ArrayList<>();
        for (Message message : messages) {
            if (LocalDate.parse(message.getDate().toString()).isAfter(LocalDate.parse(from)) || LocalDate.parse(message.getDate().toString()).isAfter(LocalDate.parse(to)))
                messagesToSend.add(message);
        }

        return formatMessage(messagesToSend, format);
    }

    private String formatMessage(List<Message> messages, String format) {
        StringBuilder messagesToSend = new StringBuilder();
        if (format.equals("xml")) {
            messagesToSend.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            messagesToSend.append("<Messages>");
            for (Message message : messages) {
                messagesToSend.append("<Message>").append(message.toString()).append("</Message>");
            }
            messagesToSend.append("</Messages>");
        } else {
            for (Message message : messages)
                messagesToSend.append(message.toString()).append('\n');
        }


        return messagesToSend.toString();
    }
}
