import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {

    final ChatManager chatManager = new ChatManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String format = request.getParameter("format");

        String messageResponse = determineResponse(from, to, format);

//        if (format != null && format.equals("xml"))
//            response.setContentType("text/xml");
//        else
//            response.setContentType("text/plain");

//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println(messageResponse);
//
//        request.getRequestDispatcher("index.html").include(request, response);
//        out.close();
//        response.reset();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String message = request.getParameter("message");

        Message newMessage = new Message(message);
        chatManager.addMessage(newMessage);
        if (user != null)
            chatManager.addUser(user);
        else
            chatManager.addUser("Anonymous");


//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println(newMessage.toString());
//
//        request.getRequestDispatcher("index.html").include(request, response);
//        out.close();
//        response.reset();

    }


    private String determineResponse(String from, String to, String format) {
        String messageResponse = "";

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
