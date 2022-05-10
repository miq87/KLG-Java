package pl.miq3l.klgjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.miq3l.klgjava.model.Person;
import pl.miq3l.klgjava.repo.PersonRepo;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Person save(Person person) {
        return personRepo.save(person);
    }

}
