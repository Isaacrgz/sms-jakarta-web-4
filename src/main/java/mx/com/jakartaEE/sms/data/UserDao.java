package mx.com.jakartaEE.sms.data;

import java.util.List;
import mx.com.jakartaEE.sms.domain.User;


public interface UserDao {
    public List<User> findAllUsers();
    
    public User findUserById(User user);
    
    public void insertUser(User user);
    
    public void updateUser(User user);
    
    public void deleteUser(User user);
}
