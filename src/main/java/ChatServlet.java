import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {

    final ChatManager chatManager = new ChatManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String format = request.getParameter("format");

        List<String> messageResponse = determineResponse(from, to, format);


        response.setContentType("text/xml");


        request.setAttribute("messages", messageResponse);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        String message = request.getParameter("message");

        Message newMessage = new Message(message);
        chatManager.addMessage(newMessage);
        if (user != null)
            chatManager.addUser(user);
        else
            chatManager.addUser("Anonymous");

        response.setStatus(response.SC_NO_CONTENT);
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println(newMessage.toString());

//        request.getRequestDispatcher("/index.jsp");
//        .include(request, response);
//        out.close();
//        response.reset();

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        chatManager.clearChat();
    }

    private List<String> determineResponse(String from, String to, String format) {
        List<String> messageResponse = new ArrayList<>();

        if (format == null && from == null && to == null) {
            messageResponse = chatManager.getAllMessagesWithinRange("plain-text");
        } else if (format == null && from != null && to != null) {
            messageResponse = chatManager.getAllMessagesWithinRange(from, to, "plain-text");
        } else if (format != null && from != null && to != null) {
            messageResponse = chatManager.getAllMessagesWithinRange(from, to, format);
        } else if (format != null && from == null && to == null) {
            messageResponse = chatManager.getAllMessagesWithinRange(format);
        }

        return messageResponse;
    }
}
