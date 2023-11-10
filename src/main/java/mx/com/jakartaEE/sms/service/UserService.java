package mx.com.jakartaEE.sms.service;

import jakarta.ejb.Local;
import java.util.List;
import mx.com.jakartaEE.sms.domain.User;

@Local
public interface UserService {
    public List<User> listUser();
    
    public User findUserById(User user);
    
    public void addUser(User user);
    
    public void modifyUser(User user);
    
    public void deleteUsuario(User user);
}
