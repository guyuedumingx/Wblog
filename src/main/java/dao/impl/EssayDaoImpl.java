package dao.impl;

import dao.EssayDao;
import domain.Essay;
import utils.JDBCUtils;
import utils.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public int getEssaysTotalNumber() {
        String sql = "select count(essay_id) total from essay";
        pstmt = JDBCUtils.getStatement(sql);
        ResultSet rs = JDBCUtils.query(pstmt);
        int integer = JDBCUtils.getInteger(rs);
        return integer;
    }

    @Override
    public List<Integer> getEssaysId() {
        String sql = "select essay_id from essay";
        pstmt = JDBCUtils.getStatement(sql);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Integer> list = JDBCUtils.getIntegerFromResultSet(rs, "essay_id");
        return list;
    }

    @Override
    public List<Essay> getEssaysIdFromPage(int page) {
        int number = Settings.essay_number_for_eachPage;
        int begin = (page - 1) * number;
        String sql = "select * from essay limit ?, ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,begin);
        JDBCUtils.setInt(pstmt,2,number);
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
    public int addEssay(Essay essay) throws SQLException {
        String sql = "insert into essay values (null,?,?,?,null,null)";
        pstmt = JDBCUtils.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        JDBCUtils.setInt(pstmt,1,essay.getUser_id());
        JDBCUtils.setString(pstmt,2,essay.getTitle());
        JDBCUtils.setString(pstmt,3,essay.getContent());
        count = pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int id = JDBCUtils.getInteger(rs);
        return id;
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
    public boolean delStar(int essay_id, int user_id) {
        String sql = "delete from star where essay_id = ? && user_id = ?";
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
        int integer = JDBCUtils.getInteger(rs);
        return integer;
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
