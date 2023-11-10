package mx.com.jakartaEE.sms.service;

import jakarta.annotation.Resource;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import java.util.List;
import mx.com.jakartaEE.sms.data.PersonDao;
import mx.com.jakartaEE.sms.domain.Person;
import jakarta.inject.Inject;

@Stateless
public class PersonServiceImpl implements PersonServiceRemote, PersonService {
    
    
    
    @Resource
    private SessionContext contexto;
    
    @Inject
    private PersonDao personDao;
    
    
    @Override
    public List<Person> listPerson () {
        return personDao.findAllPeople();
    }

    @Override
    public Person findPersonById(Person person) {
        return personDao.findPersonById(person);
    }

    @Override
    public Person findPersonByEmail(Person person) {
        return personDao.findPersonByEmail(person);
    }

    @Override
    public void addPerson(Person person) {
        try {
            personDao.insertPerson(person);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void modifyPerson(Person person) {
        try {
            personDao.updatePerson(person);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void deletePerson(Person person) {
        try {
            personDao.deletePerson(person);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
        
    }
    
}
