package Dao;

import dao.EssayDao;
import dao.impl.EssayDaoImpl;
import domain.Essay;
import org.junit.Test;
import service.impl.EssayServiceImpl;

import java.util.List;

public class EssayDaoTest {
    EssayDao dao = new  EssayDaoImpl();


    @Test
    public void addEssayTest() {
        Essay essay = new Essay(100001," goole film", "heiheihei");
        dao.addEssay(essay);
    }

    @Test
    public void getEssayTest() {
        List<Essay> essays = dao.getEssays();
    }

    @Test
    public void getStarTest() {
        int star = new EssayServiceImpl().getStar(100000);
    }
}
