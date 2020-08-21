package service;

import domain.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(int user_id);
    public boolean delUser(int user_id);
    public boolean addUser(String name, String email, String password);
    public boolean addUser(String name, String email, String img_url, String password);
    public List<User> getFollow(int user_id);
    public List<User> getFollowed(int user_id);
    public boolean setUserName(int user_id, String name);
    public boolean setUserEmail(int user_id, String email);
    public boolean addFollow(int user_id,int follow_id);
    public boolean delFollow(int user_id,int follow_id);
    public boolean isEmailExsit(String email);
    public User login(String email, String password);
    public void setUserImage(int user_id,String fileName);
}
