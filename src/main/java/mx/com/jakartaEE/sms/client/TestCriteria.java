package mx.com.jakartaEE.sms.client;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import mx.com.jakartaEE.sms.domain.Person;
import org.apache.logging.log4j.*;


public class TestCriteria {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = null;
        Root<Person> fromPerson = null;
        TypedQuery<Person> query = null;
        Person person = null;
        List<Person> people = null;
        
        //Query utilizando el API de Criteria
        //1. Consulta de todas las personas
        
        //Paso1. El objeto EntityManager crea instancia CriteriaBuilder
        cb = em.getCriteriaBuilder();
        
        //Paso2. Se crea un objeto CriteriaQuery
        criteriaQuery = cb.createQuery(Person.class);
        
        //Paso3. Creamos el objeto raiz de query
        fromPerson = criteriaQuery.from(Person.class);
        
        //Paso4. seleccionamos lo necesario del from
        criteriaQuery.select(fromPerson);
        
        //Paso5. Creamos el qyery typeSafe
        query = em.createQuery(criteriaQuery);
        
        //Paso6. Ejecutamos la consulta
        people = query.getResultList();
        
        //mostrarPersonas(personas);
        
        //2-a. Consulta de la Persona con id = 1
        //jpql = "select p from Persona p where p.idPersona = 1"
        log.debug("\n2-a. Consulta de la Persona con id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Person.class);
        fromPerson = criteriaQuery.from(Person.class);
        criteriaQuery.select(fromPerson).where(cb.equal(fromPerson.get("idPerson"), 1));
        person = em.createQuery(criteriaQuery).getSingleResult();
        //log.debug(persona);
        
        
        //2-b. Consulta de la Persona con id = 1
        log.debug("\n2-b. Consulta de la Persona con id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Person.class);
        fromPerson = criteriaQuery.from(Person.class);
        criteriaQuery.select(fromPerson);
        
        //La clase Predicate permite agregar varios criterios dinamicamente
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //Verificamos si tenemos criterios que agregar
        Integer idPersonParam = 1;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "idPerson");
        criterios.add( cb.equal(fromPerson.get("idPerson"), parameter));
        
        //Se agregan los criterios
        if(criterios.isEmpty()){
            throw new RuntimeException("Sin criterios");
        }
        else if(criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
        }
        else{
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
        
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPerson", idPersonParam);
        
        //Se ejecuta el query
        person = query.getSingleResult();
        log.debug(person);
    }

    private static void mostrarPersonas(List<Person> personas) {
        for(Person p: personas){
            log.debug(p);
        }
    }
    
}
