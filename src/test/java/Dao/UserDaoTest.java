package Dao;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import org.junit.Test;
import utils.Settings;
import java.util.List;

public class UserDaoTest {
    UserDao dao = new UserDaoImpl();
    String name = "zhaoliu";
    String email = "11111111111@gmail.com";

    @Test
    public void getUsersTest() {
        List<User> users = dao.getUsers();
    }

    @Test
    public void getUserTest() {
        UserDao dao = new UserDaoImpl();
        User user = dao.getUser(email);
    }

    @Test
    public void getFollowedTest() {
        int user_id = 100000;
        List<Integer> followedIdList = dao.getFollowedIdList(user_id);
    }

    @Test
    public void getFollowTest() {
        int user_id = 100001;
        List<Integer> followedIdList = dao.getFollowIdList(user_id);
    }

    @Test
    public void getImgPathTest() {
        String url = Settings.original_img_url;
        String path = Settings.img_path;
    }
}
