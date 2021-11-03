
package ltd.nanoda.servlet;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import ltd.nanoda.Code;
import ltd.nanoda.JwtUtil;
import ltd.nanoda.model.Message;
import ltd.nanoda.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet(
        name = "SmsServlet",
        value = {"/SmsServlet"}
)
public class SmsServlet extends HttpServlet {
    @Autowired
    MessageService messageService;

    public SmsServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
        String token = request.getParameter("token");
        if (token != null) {
            try {
                JwtUtil.verify(token);
                if (JwtUtil.isExpired(token)){
                    throw  new Exception("token过期");
                }else {
                    Message message = this.messageService.getNewMsg();
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    String phoneNumber = message.getPhoneNumber();
                    out.println("发件人:" + phoneNumber + "\n内容:" + message.getContent() + "\n发件时间:" + message.getDate());
                }


            } catch (Exception e) {
                response.getWriter().println("ERROR 001 -Token失效");
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("ERROR 003 - NOT HAVE TOKEN!");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
        System.out.println("smsDopost");
        String token = request.getParameter("token");
        //去除换行
        token =  token.replace("\r\n", "");
        if (token != null) {
            try {
                //验证token
//                Algorithm algorithm = Algorithm.HMAC256("10086cnm");
//                JWTVerifier verifier = JWT.require(algorithm).withIssuer("inxtes").acceptLeeway(10L).acceptExpiresAt(3600L).build();
//                verifier.verify(token);

                JwtUtil.verify(token);
                String json;
                if (JwtUtil.isExpired(token)){
                    throw  new Exception("token过期");
                }else {

                    List<Message> list = this.messageService.getAllMsg();
                    json = new Gson().toJson(list);
                }


                //输出数据
                response.getWriter().println(json);

//                request.setAttribute("list", list);
//                request.getRequestDispatcher("table.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println(Code.E001);
                e.printStackTrace();
            }
        } else {
            response.getWriter().println(Code.E004);
        }

    }
}
