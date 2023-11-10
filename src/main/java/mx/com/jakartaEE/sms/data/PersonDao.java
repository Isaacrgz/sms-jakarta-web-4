package mx.com.jakartaEE.sms.data;

import java.util.List;
import mx.com.jakartaEE.sms.domain.Person;


public interface PersonDao {
    public List<Person> findAllPeople();
    
    public Person findPersonById(Person person);
    
    public Person findPersonByEmail(Person person);
    
    public void insertPerson(Person person);
    
    public void updatePerson(Person person);
    
    public void deletePerson(Person person);
    
}
