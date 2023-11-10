package mx.com.jakartaEE.sms.service;

import jakarta.ejb.Remote;
import java.util.List;
import mx.com.jakartaEE.sms.domain.Person;

@Remote
public interface PersonServiceRemote {
    
    public List<Person> listPerson ();
    
    public Person findPersonById (Person person);
    
    public Person findPersonByEmail (Person person);
    
    public void addPerson (Person person);
    
    public void modifyPerson (Person person);
    
    public void deletePerson (Person person);
}
