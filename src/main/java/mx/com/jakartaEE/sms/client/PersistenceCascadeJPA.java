package mx.com.jakartaEE.sms.client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import mx.com.jakartaEE.sms.domain.Person;
import mx.com.jakartaEE.sms.domain.User;
import org.apache.logging.log4j.*;


public class PersistenceCascadeJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //Paso 1. Crea nuevo objeto
        //objeto en estado transitivo
        Person person1 = new Person("Hugo", "Hernandez", "hhernandez@mail.com", "55778822");
        
        //Crear objeto usuario (tiene dependencia con el objeto persona)
        User user1 = new User("hhernandez", "123", person1);
        
        //Paso 3. persistimos el objeto usuario unicamente
        em.persist(user1);
        
        //Paso 4. commit transaccion
        tx.commit();
        
        //objetos en estados detached
        log.debug("objeto persistido persona:" + person1);
        log.debug("objeto persistido usuario:" + user1);
        
        em.close();
        
    }
}
