import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Chat Servlet
 *
 * @author Jesse Silber
 */
@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {

    final ChatManager chatManager = new ChatManager();
    String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> messageResponse = null;
        determineProperHeaders(request);
        if ("delete".equalsIgnoreCase(request.getParameter("action"))) {
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            chatManager.clearChat(from, to);
            errorMessage = "Chat Cleared.";
            request.setAttribute("messages", new ArrayList<>());
        } else if ("download".equalsIgnoreCase(request.getParameter("action"))) {
            String type = request.getParameter("type");
            String data = chatManager.downloadLogs(type);

            response.setContentType("application/" + type);
            response.setHeader("Content-disposition", "attachment; filename=" + "chatLogs" + "." + type);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            InputStream inputStream = new ByteArrayInputStream(data.getBytes("UTF-8"));
            // get output stream of the response
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();

        } else {
            String from = request.getParameter("from");
            String to = request.getParameter("to");

            if (from == null || to == null || from.equalsIgnoreCase("") || to.equalsIgnoreCase("")) {
                errorMessage = "From or To are empty. Please provide a date in the format of: YYYY-MM-DD";
            } else {
                errorMessage = "";
                messageResponse = determineResponse(from, to);
            }
        }
        if (messageResponse == null)
            messageResponse = chatManager.getAllMessagesWithinRange();

        request.setAttribute("messages", messageResponse);
        request.setAttribute("errorMessage", errorMessage);

        response.setContentType("text/html");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        determineProperHeaders(request);
        String user = request.getParameter("user");
        String message = request.getParameter("message");

        if (message == null || message.equalsIgnoreCase("")) {
            errorMessage = "Message is empty. Please provide a non empty message.";
        } else {
            chatManager.addMessage(new Message(message, user != null ? user : "Anonymous"));
            errorMessage = "";
        }
        response.setContentType("text/html");
        request.setAttribute("errorMessage", errorMessage);

        List<String> messageResponse = chatManager.getAllMessagesWithinRange();
        request.setAttribute("messages", messageResponse);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private List<String> determineResponse(String from, String to) {
        List<String> messageResponse = new ArrayList<>();

        if (from == null && to == null) {
            messageResponse = chatManager.getAllMessagesWithinRange();
        } else if (from != null && to != null) {
            messageResponse = chatManager.getAllMessagesWithinRange(from, to);

        }

        return messageResponse;
    }

    private boolean determineProperHeaders(HttpServletRequest req) {
        boolean hasRefererHeader = false;
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            if (header != null && header.equalsIgnoreCase("Referer"))
                hasRefererHeader = true;
        }
        return hasRefererHeader;
    }
}
