package web.Servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Essay;
import service.EssayService;
import service.impl.EssayServiceImpl;
import web.Servlet.BaseSerlvet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/essayServlet")
public class EssayServlet extends BaseSerlvet {
    public void getEssay(HttpServletRequest request,HttpServletResponse response)throws IOException {
        HttpSession session = request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        Essay essay = (Essay)session.getAttribute("essay");
        response.setCharacterEncoding("UTF-8");
        String s = mapper.writeValueAsString(essay);
        mapper.writeValue(response.getWriter(),s);
    }
    public void next(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Essay essay = (Essay)session.getAttribute("essay");
        EssayService service = new EssayServiceImpl();
        Essay next = service.getNext(essay.getEssay_id());
        if(next!=null){
            session.setAttribute("essay",next);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("hasNext",next==null?false:true);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    public void previous(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Essay essay = (Essay)session.getAttribute("essay");
        EssayService service = new EssayServiceImpl();
        Essay previous = service.getPrevious(essay.getEssay_id());
        if(previous!=null){
            session.setAttribute("essay",previous);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("hasPrevious",previous==null?false:true);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
}
