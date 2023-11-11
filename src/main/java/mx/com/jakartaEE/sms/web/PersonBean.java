package mx.com.jakartaEE.sms.web;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.util.List;
import mx.com.jakartaEE.sms.domain.Person;
import mx.com.jakartaEE.sms.service.PersonService;
import org.apache.logging.log4j.*;
import org.primefaces.event.RowEditEvent;

@Named("personBean")
@RequestScoped
public class PersonBean {
    
    Logger log = LogManager.getRootLogger();
    
    //Inyecta el EJB
    @Inject
    private PersonService personService;
    
    private Person selectedPerson;
    
    List<Person> people;
    
    public PersonBean() {
    }
    
    @PostConstruct
    public void inicializar() {
        people = personService.listPerson();
        log.debug("Personas recuperadas en ManagedBean" + this.people);
        this.selectedPerson = new Person();
    }
    
    public void editListener(RowEditEvent event) {
        Person person = (Person) event.getObject();
        personService.modifyPerson(person);
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
    
    public void addPerson() {
        this.personService.addPerson(selectedPerson);
        this.people.add(selectedPerson);
        this.selectedPerson = null;
    }
    
    public void deletePerson() {
        this.personService.deletePerson(selectedPerson);
        this.people.remove(this.selectedPerson);
        this.selectedPerson = null;
    }
    
    public void restartSelectedPerson() {
        this.selectedPerson = new Person();
    }
}
