package Service;

import dao.impl.UserDaoImpl;
import org.junit.Test;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServiceTest {

    UserService service = new UserServiceImpl();
    String name = "zhaoliu";
    String email = "11111111111@gmail.com";
    String password = "111";

    @Test
    public void addUserTest() {
       service.addUser(name,email,password);
    }

    @Test
    public void delUserTest() {
        service.delUser(new UserDaoImpl().getUser(email).getUser_id());
    }

    @Test
    public void addFollowTest() {
        service.addFollow(100001,100000);
    }

    @Test
    public void delFollowTest() {
        service.delFollow(100001,100000);
    }

    @Test
    public void emailExsitTest() {
        boolean emailExsit = service.isEmailExsit(email);
    }
}
