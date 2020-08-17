package utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import domain.Essay;
import domain.User;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;

    static {
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement getStatement(String sql) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public static void close(Statement stmt) {
        try {
            close(stmt, stmt.getConnection());
        } catch (SQLException e) {
            close(null, stmt, null);
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt, Connection conn) {
        close(null, stmt,conn);
    }

    public static void close(ResultSet rs) {
        try {
            close(rs.getStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, null, null);
        }
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static ResultSet query(PreparedStatement pstmt) {
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int update(PreparedStatement pstmt) {
        int count = 0;
        try {
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    public static void setString(PreparedStatement pstmt, int index, String val) {
        try {
            pstmt.setString(index, val);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setInt(PreparedStatement pstmt, int index, int val) {
        try {
            pstmt.setInt(index, val);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Essay> getEssayFromResultSet(ResultSet rs) {
        List<Essay> list = new ArrayList<>();
        try {
           while (rs.next()) {
              int essay_id = rs.getInt("essay_id");
              int user_id = rs.getInt("user_id");
              String title = rs.getString("title");
              String content = rs.getString("content");
              String update_time = rs.getString("update_time");
              String create_time = rs.getString("create_time");
              Essay essay = new Essay(essay_id,user_id,title,content,update_time,
                      create_time);
              list.add(essay);
           }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return list;
    }

    public static List<User> getUserFromResultSet(ResultSet rs) {
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String create_time = rs.getString("create_time");
                String img_url = rs.getString("img_url");
                String password = rs.getString("password");

                user.setUser_id(user_id);
                user.setName(name);
                user.setEmail(email);
                user.setCreate_time(create_time);
                user.setImg_url(img_url);
                user.setPassword(password);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return list;
    }

    public static List<Integer> getIntegerFromResultSet(ResultSet rs, String str) {
        List<Integer> list = new ArrayList<>();
        try {
            while (rs.next()) {
                int anInt = rs.getInt(str);
                list.add(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return list;
    }
}
