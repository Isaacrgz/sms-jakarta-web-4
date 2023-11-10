package mx.com.jakartaEE.sms.service;

import jakarta.ejb.Stateless;
import java.util.List;
import mx.com.jakartaEE.sms.data.UserDao;
import mx.com.jakartaEE.sms.domain.User;
import jakarta.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService, UserServiceRemote{
    
    @Inject
    private UserDao userDao;
    
    @Override
    public List<User> listUser() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(User user) {
        return userDao.findUserById(user);
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void modifyUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUsuario(User user) {
        userDao.deleteUser(user);
    }
    
}
