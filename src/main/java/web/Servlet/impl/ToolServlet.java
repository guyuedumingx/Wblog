package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import service.impl.EssayServiceImpl;
import web.Servlet.BaseSerlvet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/toolServlet")
public class ToolServlet extends BaseSerlvet {

    public void star(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int essay_id = Integer.valueOf(request.getParameter("id"));
        HttpSession session = request.getSession();
        boolean isSuccess;
        try {
            User user = (User)session.getAttribute("user");
            isSuccess = true;
            boolean flag = new EssayServiceImpl().toggleStar(essay_id, user.getUser_id());
        }catch (Exception e) {
            isSuccess = false;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",isSuccess);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
    public void next(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        int current = getCurrent(request);
        int pages = (int)session.getAttribute("pages");
        if(current<pages)
            session.setAttribute("current",current+1);
    }
    public void previous(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        int current = getCurrent(request);
        if(current>1)
            session.setAttribute("current",current-1);
    }

    private int getCurrent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int current;
        try {
            current = (int)session.getAttribute("current");
        } catch (Exception e) {
            current = 1;
        }
        return current;
    }
}
