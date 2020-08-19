package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Essay;
import domain.User;
import service.EssayService;
import service.impl.EssayServiceImpl;
import utils.Settings;
import web.Servlet.BaseSerlvet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/indexServlet")
public class IndexServlet extends BaseSerlvet {
    public void index(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        EssayService service = new EssayServiceImpl();
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");

        int current = getCurrent(request);
        int total = service.getEssaysTotalNumber();
        int size = Settings.essay_number_for_eachPage;
        int pages = (total % size == 0) ? total / size : (total / size + 1);
        List<Essay> essays = service.getEssaysFromPage(current);
        for (Essay e : essays) {
            list.add(mapper.writeValueAsString(e));
        }

        map.put("total", total);
        map.put("size", size);
        map.put("pages", pages);
        map.put("current", current);
        map.put("essays", list);

        session.setAttribute("pages",pages);
        mapper.writeValue(response.getWriter(),map);
    }

    public void toEssay(HttpServletRequest request,HttpServletResponse response) {
        Integer essay_id = Integer.valueOf(request.getParameter("id"));
        EssayService service = new EssayServiceImpl();
        Essay essayFromId = service.getEssayFromId(essay_id);
        HttpSession session = request.getSession();
        session.setAttribute("essay",essayFromId);
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
    public void star(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int essay_id = Integer.valueOf(request.getParameter("id"));
        HttpSession session = request.getSession();
        boolean isSuccess;
        boolean flag;
        try {
           User user = (User)session.getAttribute("user");
           isSuccess = true;
           flag = new EssayServiceImpl().toggleStar(essay_id, user.getUser_id());
        }catch (Exception e) {
            isSuccess = false;
            flag = false;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",isSuccess);
        map.put("flag",flag);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
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
