package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import web.Servlet.BaseSerlvet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseSerlvet {
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String,Object> map = new HashMap<>();

        UserService service = new UserServiceImpl();
        User user = service.login(email,password);
        if(user != null) {
            map.put("isSuccess",true);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }
        else {
            map.put("isSuccess",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
    public void findUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        Map<String,Object> map = new HashMap<>();

        UserService service = new UserServiceImpl();
        if(service.isEmailExsit(email)) {
            map.put("userExsit",true);
        }
        else {
            map.put("userExsit",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
}
