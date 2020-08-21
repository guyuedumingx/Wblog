package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    public List<User> getUsers();
    public User getUser(String email);
    public User getUser(int user_id);
    public boolean addUser(String name, String email,String img_url, String password);
    public boolean delUser(int user_id);
    public List<Integer> getFollowIdList(int user_id);
    public List<Integer> getFollowedIdList(int user_id);
    public boolean setUserName(int user_id, String name);
    public boolean setUserEmail(int user_id, String email);
    public void setUserImage(int user_id, String img_url);
    public boolean addFollow(int user_id,int follow_id);
    public boolean delFollow(int user_id,int follow_id);
    public boolean setPassword(int user_id, String password);
    public User login(String email, String password);
}
