import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChatManager {


    private final String defaultFormat = "plain-text";
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<String> getAllMessagesWithinRange(String format) {
        return formatMessage(messages, format);
    }

    public List<String> getAllMessagesWithinRange(String from, String to) {
        return getAllMessagesWithinRange(from, to, defaultFormat);
    }

    public List<String> getAllMessagesWithinRange(String from, String to, String format) {
        List<Message> messagesToSend = new ArrayList<>();
        for (Message message : messages) {
            if (LocalDate.parse(message.getDate().toString()).isAfter(LocalDate.parse(from)) || LocalDate.parse(message.getDate().toString()).isAfter(LocalDate.parse(to)))
                messagesToSend.add(message);
        }

        return formatMessage(messagesToSend, format);
    }

    private List<String> formatMessage(List<Message> messages, String format) {

        List<String> stringMessages = new ArrayList<>();

        for(Message message: messages)
            stringMessages.add(message.toString());
//        StringBuilder messagesToSend = new StringBuilder();
//        if (format.equals("xml")) {
//            messagesToSend.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//            messagesToSend.append("<Messages>");
//            for (Message message : messages) {
//                messagesToSend.append("<Message>").append(message.toString()).append("</Message>");
//            }
//            messagesToSend.append("</Messages>");
//        } else {
//            for (Message message : messages)
//                messagesToSend.append(message.toString()).append('\n');
//        }

        return stringMessages;
    }

    public void clearChat() {
        messages = new ArrayList<>();
    }
}
