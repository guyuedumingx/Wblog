package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import service.impl.UserServiceImpl;
import web.Servlet.BaseSerlvet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends BaseSerlvet {
    public void register (HttpServletRequest request,HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService service = new UserServiceImpl();
        boolean flag = service.addUser(username, email, password);
        Map<String,Object> map = new HashMap<>();

        if(flag) {
            map.put("isSuccess",true);
        }
        else {
            map.put("isSuccess",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
    public void findEmail(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        UserService service = new UserServiceImpl();
        boolean emailExsit = service.isEmailExsit(email);

        Map<String,Object> map = new HashMap<>();
        if(emailExsit) {
            map.put("isExsit",true);
        }
        else {
            map.put("isExsit",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
}
