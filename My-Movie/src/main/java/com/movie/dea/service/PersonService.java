package com.movie.dea.service;

import com.movie.dea.entity.Person;
import com.movie.dea.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found!"));
    }

    public Person updatedPerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
       return personRepository.findById(id)
                       .map(existing ->  {
        existing.setName(updatedPerson.getName());
        existing.setAge(updatedPerson.getAge());
        return personRepository.save(existing);
    })
        .orElseThrow(() -> new RuntimeException("No such a person with following ID: " + id));
}

    public String deleteById(@PathVariable Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return "Person deleted!";
        }
        return "Not found!";
    }
}
