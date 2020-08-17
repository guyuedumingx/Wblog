package service.impl;

import dao.EssayDao;
import dao.impl.EssayDaoImpl;
import domain.Essay;
import service.EssayService;

import java.sql.SQLException;
import java.util.List;

public class EssayServiceImpl implements EssayService {
    EssayDao dao = new EssayDaoImpl();

    @Override
    public Essay getEssayFromId(int essay_id) {
        Essay essay = dao.getEssay(essay_id);
        return essay;
    }

    @Override
    public List<Essay> getEssays() {
        List<Essay> essays = dao.getEssays();
        return essays;
    }

    @Override
    public List<Essay> getEssays(int user_id) {
        List<Essay> essays = dao.getEssays(user_id);
        return essays;
    }

    @Override
    public Essay addEssay(Essay essay) {
        Essay essayBack = null;
        try {
            int essay_id = dao.addEssay(essay);
            essayBack = dao.getEssay(essay_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return essayBack;
    }

    @Override
    public boolean delEssay(int essay_id) {
        return false;
    }

    @Override
    public boolean addStar(int essay_id, int user_id) {
        boolean isSuccess = dao.addStar(essay_id,user_id);
        return isSuccess;
    }

    @Override
    public boolean addFavorite(int essay_id, int favorite_id) {
        boolean isSuccess = dao.addFavorite(essay_id, favorite_id);
        return isSuccess;
    }

    @Override
    public int getStar(int essay_id) {
       return dao.getStar(essay_id);
    }
}
