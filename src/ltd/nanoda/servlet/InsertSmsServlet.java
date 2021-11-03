
package ltd.nanoda.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ltd.nanoda.model.Message;
import ltd.nanoda.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet(
        name = "InsertSmsServlet",
        value = {"/InsertSmsServlet"}
)
public class InsertSmsServlet extends HttpServlet {
    @Autowired
    MessageService messageService;

    public InsertSmsServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
        InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
        StringBuilder json = new StringBuilder();

        for(int respInt = inputStreamReader.read(); respInt != -1; respInt = inputStreamReader.read()) {
            json.append((char)respInt);
        }

        Message message = (Message)(new Gson()).fromJson(json.toString(), Message.class);
        this.messageService.insertMsg(message);
    }
}
