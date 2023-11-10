package mx.com.jakartaEE.sms.data;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import mx.com.jakartaEE.sms.domain.Person;

@Stateless
public class PersonDaoImpl implements PersonDao {
    
    @PersistenceContext(unitName = "SgaPU")
    EntityManager em;

    @Override
    public List<Person> findAllPeople() {
        return em.createNamedQuery("Person.findAll").getResultList();
    }

    @Override
    public Person findPersonById(Person person) {
        return em.find(Person.class, person.getIdPerson());
    }

    @Override
    public Person findPersonByEmail(Person person) {
        Query query = em.createQuery("FROM Person p WHERE p.email =: email");
        query.setParameter("email", person.getEmail());
        return (Person) query.getSingleResult();
    }

    @Override
    public void insertPerson(Person person) {
        em.persist(person);
    }

    @Override
    public void updatePerson(Person person) {
       em.merge(person);
    }

    @Override
    public void deletePerson(Person person) {
        em.remove(em.merge(person));
    }
    
    
    
}
