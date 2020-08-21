package service.impl;

import dao.EssayDao;
import dao.impl.EssayDaoImpl;
import domain.Essay;
import service.EssayService;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EssayServiceImpl implements EssayService {
    EssayDao dao = new EssayDaoImpl();
    Connection conn;

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
    public List<Essay> getEssaysFromPage(int page) {
        List<Essay> essaysIdFromPage = dao.getEssaysIdFromPage(page);
        return essaysIdFromPage;
    }

    @Override
    public int getEssaysTotalNumber() {
       return dao.getEssaysTotalNumber();
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
    public boolean toggleStar(int essay_id, int user_id) {
        boolean isSuccess = dao.addStar(essay_id,user_id);
        if(!isSuccess)
            dao.delStar(essay_id,user_id);
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

    @Override
    public Essay getNext(int essay_id) {
        List<Integer> next = dao.getNext(essay_id);
        Essay essay;
        if(next.isEmpty())
            essay = null;
        else {
            essay = dao.getEssay(next.get(0));
        }
        return essay;
    }
    @Override
    public Essay getPrevious(int essay_id) {
        List<Integer> next = dao.getNext(essay_id);
        Essay essay;
        if(next.isEmpty())
            essay = null;
        else {
            essay = dao.getEssay(next.get(0));
        }
        return essay;
    }

    @Override
    public List<Essay> getStarEssays(int user_id) {
        List<Integer> idList = dao.getStarEssaysList(user_id);
        List<Essay> essayList = new ArrayList<>();
        for(int id : idList) {
            Essay essay = dao.getEssay(id);
            essayList.add(essay);
        }
        return essayList;
    }

    @Override
    public boolean delEssay(int essay_id) {
        conn = JDBCUtils.getConnection();
        boolean flag = false;
        try {
            conn.setAutoCommit(false);
            boolean b = dao.delEssay(essay_id);
            boolean b1 = dao.delStar(essay_id);
            conn.commit();
            conn.setAutoCommit(true);
            flag = true;
        }catch (Exception e) {
            try {
                conn.rollback();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JDBCUtils.close(null,null,conn);
        }
        return flag;
    }

    @Override
    public List<Essay> getEssayList(String content) {
        return dao.getEssayList(content);
    }

    @Override
    public Essay updateEssay(int id, Essay essay) {
        Essay backEssay = null;
        Connection conn = JDBCUtils.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.updateEssay(id, essay);
            backEssay = dao.getEssay(id);
            conn.commit();
            conn.setAutoCommit(true);
        }catch (Exception e) {
            try {
                conn.rollback();
            }catch (Exception e1) {
                e.printStackTrace();
            }
        }finally {
            JDBCUtils.close(null,null,conn);
        }
       return backEssay;
    }
}
