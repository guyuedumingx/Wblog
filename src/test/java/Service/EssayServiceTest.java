package Service;

import domain.Essay;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import service.EssayService;
import service.impl.EssayServiceImpl;
import sun.java2d.SurfaceDataProxy;

import java.util.List;

public class EssayServiceTest {
    EssayService service = new EssayServiceImpl();

    @Test
    public void addEssayTest() {
        Essay essay = new Essay(100001," goole film", "heiheihei");
        Essay essayBack = service.addEssay(essay);
    }

    @Test
    public void getEssayTest() {
        Essay essayFromId = service.getEssayFromId(100011);
        System.out.println(essayFromId.getContent());
    }

    @Test
    public void getEssayFromPageTest() {
        List<Essay> essaysFromPage = service.getEssaysFromPage(2);
        for(Essay essay: essaysFromPage) {
            int essay_id = essay.getEssay_id();
            System.out.println(essay_id);
        }
    }

}
