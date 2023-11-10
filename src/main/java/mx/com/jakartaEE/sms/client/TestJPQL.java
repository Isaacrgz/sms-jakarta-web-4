package mx.com.jakartaEE.sms.client;

import java.util.Iterator;
import java.util.List;
import jakarta.persistence.*;
import mx.com.jakartaEE.sms.domain.Person;
import mx.com.jakartaEE.sms.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestJPQL {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        String jpql = null;
        Query q = null;
        List<Person> people = null;
        Person person = null;
        Iterator iter = null;
        Object[] tupla = null;
        List<String> names = null;
        List<User> users = null;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        //1. Consulta de todos los objetos de tipo Persona
        log.debug("\n1. Consulta de todas las Personas");
        jpql = "select p from Person p";
        people = em.createQuery(jpql).getResultList();
        //mostrarPersonas( personas );
        
        //2. Consula de la Persona con id = 1
        log.debug("\n2. consulta de la Persona con id = 1");
        jpql = "select p from Person p where p.idPerson = 1";
        person = (Person) em.createQuery(jpql).getSingleResult();
        //log.debug(persona);
        
        
        //3. Consulta de la Persona por nombre
        jpql = "select p from Person p where p.name = 'Karla'";
        people = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //4. Consulta de datos individuales, se crea un arreglo(tupla) de tipo object de 3 columnas
        log.debug("\n4. Consulta de datos individuales, se crea un arreglo (tupla) de tipo object de 3 columnas");
        jpql = "select p.name as Name, p.lastname as Lastname, p.email as Email from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            String name = (String) tupla[0];
            String lastname = (String) tupla[1];
            String email = (String) tupla[2];
            //log.debug("nombre:" + nombre + ", apellido:" + apellido + ", email:" + email) ;
        }
        
        //5. Obtiene el objeto Persona y el id, se crea un arreglo de tipo Object con 2 columnas
        log.debug("\n. Obtiene el objeto Persona y el id, se crea un arreglo de tipo Object con 2 columnas");
        jpql = "select p, p.idPerson from Person p ";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            person = (Person) tupla[0];
            int idPerson = (int) tupla[1];
            //log.debug("Objeto persona:" + persona);
            //log.debug("id persona:" + idPersona );
        }
        
        //6. Consulta de todas las personas
        System.out.println("\n6. Consulta de todas las personas");
        jpql = "select new mx.com.jakartaEE.sms.domain.Person( p.idPerson ) from Person p";
        people = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //7. Regresa el valor minimo y maxico del idPersona (scaler result)
        System.out.println("\n7. Regresa el valor minimo y maxico del idPersona (scaler result)");
        jpql = "select min(p.idPerson) as MinId, max(p.idPerson) as MaxId, count(p.idPerson) as Counter from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            Integer idMin = (Integer) tupla[0];
            Integer idMax = (Integer) tupla[1];
            Long count = (Long) tupla[2];
            //log.debug("idMin:" + idMin + ", idMax:" + idMax + ", count:" + count);
        }
        
        //8. Cuenta los nombres de las personas que son distintos
        log.debug("\n8. Cuenta los nombres de las personas que son distintos");
        jpql = "select count(distinct p.name) from Persona p";
        Long contador = (Long) em.createQuery(jpql).getSingleResult();
        //log.debug("no. de personas con nombre distinto:" + contador);
        
        //9. Concatena y convierte a mayusculas el nombre y apellido
        log.debug("\n9. Concatena y convierte a mayusculas el nombre y apellido");
        jpql = "select CONCAT(p.name, ' ', p.lastname) as Name from Person p";
        names = em.createQuery(jpql).getResultList();
        for(String n: names){
            //log.debug(nombreCompleto);
        }
        
        //10. Obtiene el objeto persona con id igual al parametro proporcionado
        log.debug("\n10. Obtiene el objeto persona con id igual al parametro proporcionado");
        int idPerson = 5;
        jpql = "select p from Person p where p.idPerson = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPerson);
        people = (List<Person>) (Person) q.getSingleResult();
        //log.debug(persona);
        
        //11. Obtiene las personas que contengan una letra a en el nombre, sin importar si es mayusculas o minuscula
        log.debug("\n11. Obtiene las personas que contengan una letra a en el nombre, sin importar si es mayusculas o minuscula");
        jpql = "select p from Person p where upper(p.name) like upper(:parametro)";
        String parametroString = "%a%";//es el caracter que utilizamos para el like
        q = em.createQuery(jpql);
        q.setParameter("parametro", parametroString);
        people = q.getResultList();
        //mostrarPersonas(personas);
        
        //12. Uso de between
        log.debug("\n12. Uso de between");
        jpql = "select p from Person p where p.idPerson between 1 and 10";
        people = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //13. Uso del ordenamiento
        log.debug("\n13. Uso del ordenamiento");
        jpql = "select p from Person p where p.idPerson > 1 order by p.name desc, p.lastname desc";
        people = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //14. Uso de subquery 
        log.debug("\n14. Uso de subquery");
        jpql = "select p from Person p where p.idPerson in (select min(p1.idPerson) from Person p1)";
        people = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //15. Uso de join con lazy loading
        log.debug("\n15. Uso de join con lazy loading");
        jpql = "select u from User u join u.person p";
        users = em.createQuery(jpql).getResultList();
        //mostrarUsuarios(usuarios);
        
        //16. Uso de left join con eager loading
        log.debug("16. Uso de left join con eager loading");
        jpql = "select u from User u left join fetch u.person";
        users = em.createQuery(jpql).getResultList();
        showUsers(users);
    }
    
    private static void showPeople (List<Person> people) {
        for (Person p : people) {
            log.debug(p);
        }
    }
    private static void showUsers (List<User> users) {
        for (User u : users) {
            log.debug(u);
        }
    }
}
