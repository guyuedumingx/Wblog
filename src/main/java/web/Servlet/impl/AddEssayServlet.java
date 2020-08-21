package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Essay;
import domain.User;
import service.EssayService;
import service.UserService;
import service.impl.EssayServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addEssayServlet")
public class AddEssayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        EssayService service = new EssayServiceImpl();
        Essay essayBack = null;
        String content = request.getParameter("essay");
        String title = request.getParameter("title");
        int id = Integer.valueOf(request.getParameter("id"));
        Essay essay = new Essay(user.getUser_id(),title,content);
        if(user != null) {
            if(id!=-1){
                essayBack = service.updateEssay(id,essay);
            }else {
                essayBack = service.addEssay(essay);
            }
            map.put("isSuccess", essayBack==null?false:true);
        }else {
            map.put("isSuccess",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
        session.setAttribute("essay",essayBack);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
