package ltd.nanoda.servlet;

import com.google.gson.Gson;
import ltd.nanoda.Util.FileEachUtil;
import ltd.nanoda.model.FileCollection;
import ltd.nanoda.model.FolderCollection;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@WebServlet(name = "FileServlet", value = "/FileServlet",initParams = {
        @WebInitParam(name = "listings",value = "true")
})
@MultipartConfig
public class FileServlet extends HttpServlet {
    private static final String ROOTPATH = "/home/nete";//跟目录


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        String path = request.getParameter("path");
        path = ROOTPATH+path;
        System.out.println(path);
        //遍历子文件
        FileEachUtil.Collection collection = new FileEachUtil().each(path);
        PrintWriter out = response.getWriter();

        if (collection==null){
            out.println("{\"type\":\"blank\"}");
        }else {

            //返回
            String json;
            json = new Gson().toJson(collection);
            out.println(json);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        //取得文件
        Part filePart = request.getPart("file");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.println(fileName);
        String[] str = fileName.split("\\.");
        String end = str[str.length-1];
        //获得文件后缀
        end = end.toUpperCase(Locale.ROOT);

        PrintWriter out = response.getWriter();
        //路径
        String path = ROOTPATH+"/file/"+end+"/";
        java.io.File file = new java.io.File(path);
        if (!file.exists()){
            //检查文件夹是否存在 不存在就创建
            file.mkdirs();
        }

        Path filePath = Paths.get(file.getPath(),fileName);

        try (InputStream inputStream = filePart.getInputStream()){
            //输出
            Files.copy(inputStream,filePath);
        }catch (Exception e){
            e.printStackTrace();
            out.println(e.fillInStackTrace());
        }
        out.println("Ok");






    }
}
