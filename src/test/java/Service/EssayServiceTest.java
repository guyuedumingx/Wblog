package Service;

import domain.Essay;
import org.junit.Test;
import service.EssayService;
import service.impl.EssayServiceImpl;
import utils.Settings;

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
        Essay essayFromId = service.getEssayFromId(100069);
        System.out.println(essayFromId.getContent());
    }

    @Test
    public void getEssayFromPageTest() {
        List<Essay> essaysFromPage = service.getEssaysFromPage(2);
    }

    @Test
    public void getTotalNumber() {
        int essaysTotalNumber = service.getEssaysTotalNumber();
    }
    @Test
    public void getPath(){
        System.out.println(Settings.img_path);
    }
}
