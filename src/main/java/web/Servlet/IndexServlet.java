package web.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Essay;
import service.EssayService;
import service.impl.EssayServiceImpl;
import utils.Settings;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        EssayService service = new EssayServiceImpl();
        response.setCharacterEncoding("UTF-8");

        int current =Integer.valueOf(request.getParameter("current"));
        int total = service.getEssaysTotalNumber();
        int size = Settings.essay_number_for_eachPage;
        List<Essay> essays = service.getEssaysFromPage(current);
        for (Essay e : essays) {
            list.add(mapper.writeValueAsString(e));
        }
        map.put("total",total);
        map.put("size", size);
        map.put("pages",(total%size==0)?total/size:(total/size+1));
        map.put("current",current);
        map.put("essays",list);
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
