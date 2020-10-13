import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Chat Manager
 *
 * @author Jesse Silber
 */
public class ChatManager {


    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<String> getAllMessagesWithinRange() {
        return formatMessage(messages);
    }

    public List<String> getAllMessagesWithinRange(String from, String to) {

        String fromDate = from.substring(0,from.indexOf("T"));
        String fromTo = to.substring(0,from.indexOf("T"));

        String fromTime = from.substring(from.indexOf("T")+1);
        String toTime = from.substring(to.indexOf("T")+1);

        List<Message> messagesToSend = new ArrayList<>();
        for (Message message : messages) {
            if (LocalDate.parse(message.getDate().toString()).isAfter(LocalDate.parse(fromDate)) || LocalDate.parse(message.getDate().toString()).isBefore(LocalDate.parse(fromTo)))
                messagesToSend.add(message);
            else if((LocalDate.parse(message.getDate().toString())==(LocalDate.parse(fromDate)) || LocalDate.parse(message.getDate().toString())==(LocalDate.parse(fromTo)))){
                if(LocalTime.parse(message.getTime()).isAfter(LocalTime.parse(fromTime)) || LocalTime.parse(message.getTime()).isBefore(LocalTime.parse(toTime))){
                    messagesToSend.add(message);
                }
            }
        }

        return formatMessage(messagesToSend);
    }

    public void clearChat(String from, String to) {
        if (from == null || to == null || from.equalsIgnoreCase("") || to.equalsIgnoreCase(""))
            messages = new ArrayList<>();
        else {
            List<Message> messagesToKeep = new ArrayList<>();

            for (Message message : messages) {
                if (LocalDate.parse(message.getDate().toString()).isBefore(LocalDate.parse(from)) || LocalDate.parse(message.getDate().toString()).isAfter(LocalDate.parse(to)))
                    messagesToKeep.add(message);
            }
            messages = messagesToKeep;
        }
    }

    public String downloadLogs(String type) {
        StringBuilder messagesToSend = new StringBuilder();
        if (type != null && type.equals("xml")) {
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

    private List<String> formatMessage(List<Message> messages) {
        List<String> stringMessages = new ArrayList<>();

        for (Message message : messages)
            stringMessages.add(message.toString());
        return stringMessages;
    }


}
