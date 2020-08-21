package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Essay;
import domain.User;
import service.UserService;
import service.impl.EssayServiceImpl;
import service.impl.UserServiceImpl;
import web.Servlet.BaseSerlvet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userServlet")
public class UserServlet extends BaseSerlvet {
    public void index(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Map<String,Object> map = null;
        if(user != null) {
            map = new HashMap<>();
            map.put("username",user.getName());
            map.put("email", user.getEmail());
            map.put("img_url",user.getImg_url());
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
    public void exit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
    }
    public void star(HttpServletRequest request,HttpServletResponse response) throws IOException {
       response.setCharacterEncoding("UTF-8");
       response.setHeader("content-type","text/html;charset=UTF-8");
       HttpSession session = request.getSession();
       User user = (User)session.getAttribute("user");
       List<Essay> starEssays = new EssayServiceImpl().getStarEssays(user.getUser_id());
       ObjectMapper mapper = new ObjectMapper();
       mapper.writeValue(response.getWriter(),starEssays);

    }
    public void getUsers(HttpServletRequest request,HttpServletResponse response) throws IOException {
        UserService service = new UserServiceImpl();
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = service.getUsers();
        List<String> back = new ArrayList<>();
        for(User user : users) {
            String s = mapper.writeValueAsString(user);
            back.add(s);
        }
        mapper.writeValue(response.getWriter(),back);
    }
    public void delUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id =Integer.valueOf(request.getParameter("id"));
        UserService service = new UserServiceImpl();
        service.delUser(id);
    }
    public void article(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Essay> starEssays = new EssayServiceImpl().getEssays(user.getUser_id());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),starEssays);
    }
}
