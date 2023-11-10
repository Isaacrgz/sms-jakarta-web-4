package mx.com.jakartaEE.sms.data;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import mx.com.jakartaEE.sms.domain.User;

@Stateless
public class UserDaoImpl implements UserDao{
    
    @PersistenceContext(unitName="SgaPU")
    EntityManager em;
    
    @Override
    public List<User> findAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findUserById(User user) {
        return em.find(User.class, user.getIdUser());
    }

    @Override
    public void insertUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(em.merge(user));
    }
    
}
