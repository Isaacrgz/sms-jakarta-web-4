package mx.com.jakartaEE.sms.client;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.*;
import java.util.List;
import mx.com.jakartaEE.sms.domain.Person;
import mx.com.jakartaEE.sms.domain.User;
import org.apache.logging.log4j.*;



public class TestAsociacionesJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        List<Person> people = em.createNamedQuery("Person.findAll").getResultList();
        
        //cerramos la conexion
        em.close();
        
        //Imprimir los objetos de tipo persona
        for(Person p : people){
            log.debug("Person: " + p);
            //recuperamos los usuarios de cada persona
            for(User usuario: p.getUserList()){
                log.debug("User: " + usuario);
            }
        }
    }
}
