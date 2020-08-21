package web.Servlet.impl;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Settings;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/uploadFileServlet")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("image");
        String fileName = getFileName(part);
        String realPath = request.getSession().getServletContext().getRealPath("")+"img/";
        mkDir(realPath);
        writeTo(realPath+fileName,part);
        UserService service = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        service.setUserImage(user.getUser_id(),fileName);
        session.setAttribute("user",service.getUser(user.getUser_id()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    private String getFileName(Part part) {
        String head = part.getHeader("Content-Disposition");
        String fileName = head.substring(head.indexOf("filename=\"") + 10, head.lastIndexOf("\""));
        return fileName;
    }
    private void writeTo(String fileName, Part part)throws IOException {
        InputStream in = part.getInputStream();
        OutputStream out = new FileOutputStream(fileName);
        byte[] b = new byte[1024];
        int length = -1;
        while((length = in.read(b))!=-1) {
                out.write(b, 0, length);
        }
        in.close();
        out.close();
    }
    private void mkDir(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
