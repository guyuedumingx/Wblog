package web.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Essay;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getEssayServlet")
public class GetEssayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Essay essay = (Essay)session.getAttribute("essay");
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> map = new HashMap<>();
        map.put("title",essay.getTitle());
        map.put("content",essay.getContent());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
