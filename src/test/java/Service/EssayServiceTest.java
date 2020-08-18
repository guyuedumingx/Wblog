package Service;

import domain.Essay;
import org.junit.Test;
import service.EssayService;
import service.impl.EssayServiceImpl;

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
        Essay essayFromId = service.getEssayFromId(100000);
        System.out.println(essayFromId.getContent());
    }

    @Test
    public void getEssayFromPageTest() {
        List<Essay> essaysFromPage = service.getEssaysFromPage(2);
    }

    @Test
    public void getTotalNumber() {
        int essaysTotalNumber = service.getEssaysTotalNumber();
        System.out.println(essaysTotalNumber);
    }
}
