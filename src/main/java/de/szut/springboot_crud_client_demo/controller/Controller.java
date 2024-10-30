package de.szut.springboot_crud_client_demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.szut.springboot_crud_client_demo.model.Person;
import jakarta.servlet.http.PushBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/springboot-crud-service/person")
@CrossOrigin(origins = "http://localhost:8081")
public class Controller {

    private final HashMap<Integer, Person> personHashMap = new HashMap<>();

    public Controller() {
        personHashMap.put(1, new Person(1, "John", "Doe"));
        personHashMap.put(2, new Person(2, "Jane", "Doe"));
        personHashMap.put(3, new Person(3, "Jack", "Doe"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
        Person person = personHashMap.get(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(new ArrayList<>(personHashMap.values()));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        personHashMap.put(person.getId(), person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable("id") int id) {
        Person person = personHashMap.get(id);
        if (person != null) {
            personHashMap.remove(id);
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
