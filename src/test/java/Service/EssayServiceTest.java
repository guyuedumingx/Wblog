package Service;

import domain.Essay;
import org.junit.Test;
import service.EssayService;
import service.impl.EssayServiceImpl;

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

}
