package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;
import utils.Settings;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Override
    public boolean addUser(String name, String email, String password) {
        String img_url = Settings.original_img_url;
        boolean flag = addUser(name, email, img_url, password);
        return flag;
    }

    @Override
    public boolean addUser(String name, String email, String img_url, String password) {
        return dao.addUser(name,email,img_url, password);
    }

    @Override
    public boolean delUser(int user_id) {
       return dao.delUser(user_id);
    }

    @Override
    public boolean setUserName(int user_id, String name) {
       return dao.setUserName(user_id,name);
    }

    @Override
    public boolean setUserEmail(int user_id, String email) {
        return dao.setUserEmail(user_id,email);
    }

    @Override
    public boolean addFollow(int user_id, int follow_id) {
        return dao.addFollow(user_id,follow_id);
    }

    @Override
    public boolean delFollow(int user_id, int follow_id) {
        return dao.delFollow(user_id,follow_id);
    }

    @Override
    public List<User> getFollow(int user_id) {

        List<Integer> idList = dao.getFollowIdList(user_id);
        List<User> followList = new ArrayList<>();
        for(Integer id : idList) {
            User user = dao.getUser(id);
            followList.add(user);
        }
        return followList;
    }

    @Override
    public List<User> getFollowed(int user_id) {
        List<Integer> idList = dao.getFollowedIdList(user_id);
        List<User> followList = new ArrayList<>();
        for(Integer id : idList) {
            User user = dao.getUser(id);
            followList.add(user);
        }
        return followList;
    }

    @Override
    public boolean isEmailExsit(String email) {
        User user = dao.getUser(email);
        if(user != null)
            return true;
        else
            return false;
    }

    @Override
    public User login(String email, String password) {
        User user = dao.login(email, password);
        return user;
    }

    @Override
    public void setUserImage(int user_id, String fileName) {
        dao.setUserImage(user_id,Settings.img_path+fileName);
    }

    @Override
    public User getUser(int user_id) {
        User user = dao.getUser(user_id);
        return user;
    }
}
