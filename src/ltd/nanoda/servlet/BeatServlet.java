package ltd.nanoda.servlet;

import com.google.gson.Gson;
import ltd.nanoda.BeatDate;
import ltd.nanoda.model.Device;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(name = "BeatServlet", value = "/BeatServlet")
public class BeatServlet extends HttpServlet {
    static Device device = new Device("不在线");
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            long nowTime = System.currentTimeMillis();
            if ((BeatDate.getLastLoginTime()) != 0) {
                System.out.println(nowTime);
                System.out.println(BeatDate.getLastLoginTime());
                System.out.println(nowTime - BeatDate.getLastLoginTime());
                if ((nowTime - BeatDate.getLastLoginTime()) > 60000*10) {
                    //登录过期
                    device.setOnline("不在线");
                    BeatDate.setLastLoginTime(0);

                }else {

                    device.setOnline("在线");
                }
            }


        }
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String flag = request.getParameter("flag");
        System.out.println("BeatAction");

        if (flag!=null) {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            if (flag.equals("table")) {
                PrintWriter out = response.getWriter();
                String json = new Gson().toJson(device);
                out.println(json);
            } else if (flag.equals("client")){
                System.out.println("getClientBeat");
                if (BeatDate.getLastLoginTime() == 0) {
                    BeatDate.setLastLoginTime(System.currentTimeMillis());
                    device.setOnline("在线");
                    Timer timer = new Timer();
                    timer.schedule(timerTask, 0,60000*5);
                }

                BeatDate.setLastLoginTime(System.currentTimeMillis());
            }
        }else {
            PrintWriter out = response.getWriter();
            out.println("BeatServlet");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
