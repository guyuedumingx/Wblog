package dao.impl;

import dao.EssayDao;
import domain.Essay;
import utils.JDBCUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EssayDaoImpl implements EssayDao {
    private PreparedStatement pstmt = null;
    private int count;

    @Override
    public Essay getEssay(int essay_id) {
        String sql = "select * from essay where essay_id = ? ";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,essay_id);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Essay> list = JDBCUtils.getEssayFromResultSet(rs);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Essay> getEssays() {
        String sql = "select * from essay";
        pstmt = JDBCUtils.getStatement(sql);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Essay> list = JDBCUtils.getEssayFromResultSet(rs);
        return list;
    }

    @Override
    public List<Essay> getEssays(int user_id) {
        String sql = "select * from essay where user_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,user_id);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Essay> list = JDBCUtils.getEssayFromResultSet(rs);
        return list;
    }

    @Override
    public boolean addEssay(Essay essay) {
        String sql = "insert into essay values (null,?,?,?,null,null)";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,essay.getUser_id());
        JDBCUtils.setString(pstmt,2,essay.getTitle());
        JDBCUtils.setString(pstmt,3,essay.getContent());
        count = JDBCUtils.update(pstmt);
        return count== 0 ? false : true;
    }

    @Override
    public boolean addStar(int essay_id, int user_id) {
        String sql = "insert ignore into star values (?,?)";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,essay_id);
        JDBCUtils.setInt(pstmt,2,user_id);
        count = JDBCUtils.update(pstmt);
        return count== 0 ? false : true;
    }

    @Override
    public int getStar(int essay_id) {
        String sql = "select count(user_id) star from star where essay_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,essay_id);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Integer> list = JDBCUtils.getIntegerFromResultSet(rs,"star");
        return list.isEmpty() ? 0 : list.get(0);
    }

    @Override
    public boolean addFavorite(int essay_id, int favorite_id) {
        String sql = "insert ignore into favorite values (?, ?)";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,favorite_id);
        JDBCUtils.setInt(pstmt,2,essay_id);
        count = JDBCUtils.update(pstmt);
        return count== 0 ? false : true;
    }
}
