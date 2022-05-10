package pl.miq3l.klgjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.miq3l.klgjava.model.Person;
import pl.miq3l.klgjava.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

}
