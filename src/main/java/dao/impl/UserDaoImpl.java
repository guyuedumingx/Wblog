package dao.impl;

import dao.UserDao;
import domain.User;
import utils.JDBCUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private int count = 0;

    @Override
    public List<User> getUsers() {
        String sql = "select * from user";
        pstmt = JDBCUtils.getStatement(sql);
        rs = JDBCUtils.query(pstmt);
        List<User> list = JDBCUtils.getUserFromResultSet(rs);
        return list;
    }

    @Override
    public User getUser(String email) {
        String sql = "select * from user where email = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setString(pstmt,1,email);
        rs = JDBCUtils.query(pstmt);
        List<User> list = JDBCUtils.getUserFromResultSet(rs);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public User getUser(int user_id) {
        String sql = "select * from user where user_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,user_id);
        rs = JDBCUtils.query(pstmt);
        List<User> list = JDBCUtils.getUserFromResultSet(rs);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean addUser(String name, String email,String img_url, String password) {
        String sql = "insert into user values (null,?,?,?,null,?)";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setString(pstmt,1,name);
        JDBCUtils.setString(pstmt,2,email);
        JDBCUtils.setString(pstmt,3,img_url);
        JDBCUtils.setString(pstmt,4,password);
        count = JDBCUtils.update(pstmt);
        return count == 0 ? false : true;
    }

    @Override
    public boolean delUser(int user_id) {
        String sql = "delete from user where user_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,user_id);
        count = JDBCUtils.update(pstmt);
        return count == 0 ? false : true;
    }

    @Override
    public List<Integer> getFollowIdList(int user_id) {
        String sql = "select * from follow where user_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,user_id);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Integer> list = JDBCUtils.getIntegerFromResultSet(rs, "follow_id");
        return list;
    }

    @Override
    public List<Integer> getFollowedIdList(int user_id) {
        String sql = "select * from follow where follow_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,user_id);
        ResultSet rs = JDBCUtils.query(pstmt);
        List<Integer> list = JDBCUtils.getIntegerFromResultSet(rs, "user_id");
        return list;
    }

    @Override
    public boolean setUserName(int user_id, String name) {
        String sql = "update user set name = ? where user_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setString(pstmt,1,name);
        JDBCUtils.setInt(pstmt,2,user_id);
        count = JDBCUtils.update(pstmt);
        return count == 0 ? false : true;
    }

    @Override
    public boolean setUserEmail(int user_id, String email) {
        String sql = "update user set email = ? where user_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setString(pstmt,1,email);
        JDBCUtils.setInt(pstmt,2,user_id);
        count = JDBCUtils.update(pstmt);
        return count == 0 ? false : true;
    }

    @Override
    public boolean addFollow(int user_id, int follow_id) {
       String sql = "insert into follow values(?,?)";
       pstmt = JDBCUtils.getStatement(sql);
       JDBCUtils.setInt(pstmt,1,user_id);
       JDBCUtils.setInt(pstmt,2,follow_id);
       count = JDBCUtils.update(pstmt);
       return count == 0 ? false : true;
    }

    @Override
    public boolean delFollow(int user_id, int follow_id) {
        String sql = "delete from follow where user_id = ? && follow_id = ?";
        pstmt = JDBCUtils.getStatement(sql);
        JDBCUtils.setInt(pstmt,1,user_id);
        JDBCUtils.setInt(pstmt,2,follow_id);
        count = JDBCUtils.update(pstmt);
        return count == 0 ? false : true;
    }

    @Override
    public boolean setPassword(int user_id, String password) {
       String sql = "update user password = ? where user_id = ?";
       pstmt = JDBCUtils.getStatement(sql);
       JDBCUtils.setString(pstmt,1,password);
       JDBCUtils.setInt(pstmt,2,user_id);
       count = JDBCUtils.update(pstmt);
       return count == 0 ? false : true;
    }

    @Override
    public User login(String email, String password) {
       String sql = "select * from user where email = ? && password = ?";
       pstmt = JDBCUtils.getStatement(sql);
       JDBCUtils.setString(pstmt,1,email);
       JDBCUtils.setString(pstmt,2,password);
       ResultSet rs = JDBCUtils.query(pstmt);
       List<User> list = JDBCUtils.getUserFromResultSet(rs);
       return list.isEmpty() ? null : list.get(0);
    }
}
