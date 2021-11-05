

package ltd.nanoda.servlet;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import ltd.nanoda.Code;
import ltd.nanoda.HashUtils;
import ltd.nanoda.JwtUtil;
import ltd.nanoda.model.User;
import ltd.nanoda.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet(
        name = "LoginServlet",
        value = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
    @Autowired
    LoginService loginService;

    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        System.out.println("LoginDoPost");
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
        String username = request.getParameter("username");
        String password = request.getParameter("passwd");
        User user = new User();
        user.setUserName(username);
        user.setPassWd(password);
        if (this.loginService.checkUserInfo(user) == 1) {
            try {
                Map map = new TreeMap();
                map.put("signer", UUID.randomUUID());
                map.put("username",username);

                String token = JwtUtil.generate(map);
                response.getWriter().println(token);

            } catch (Exception e) {
                response.getWriter().println(Code.E002);
                e.printStackTrace();
            }
        } else {
            response.getWriter().println(Code.E003);
        }

    }


    /**
     * -------------------废弃--------------------
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        System.out.println("LoginDoPost");
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(username);
        user.setPassWd(password);
        //签发Token
        if (this.loginService.checkUserInfo(user) == 1) {
            try {
//                Algorithm algorithm = Algorithm.HMAC256("10086cnm");
//                String token = JWT.create().withIssuer("inxtes").sign(algorithm);
//                SecretKey key = Keys.secretKeyFor(SignatureAlgorithm)
//                String token =  JwtUtil.generate();

//                response.getWriter().println(token);

            } catch (Exception e) {
                response.getWriter().println("ERROR 002");
                e.printStackTrace();
            }
        } else {
            response.getWriter().println(Code.E003);
        }

    }
}
