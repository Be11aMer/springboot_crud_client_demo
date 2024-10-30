package de.szut.springboot_crud_client_demo.app;

import java.util.List;

import de.szut.springboot_crud_client_demo.dao.PersonDao;
import de.szut.springboot_crud_client_demo.model.Person;
import org.springframework.web.server.ResponseStatusException;

public class PersonDaoTest {

    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();

        // Test read method
        try {
            Person person = personDao.read(1);
            System.out.println("Read person with ID 1: " + person);
        } catch (ResponseStatusException e) {
            System.err.println("Failed to read person with ID 1: " + e.getMessage());
        }

        // Test readAll method
        try {
            List<Person> person = List.of(personDao.readAll());
            System.out.println("Read all persons: " + person);
        } catch (ResponseStatusException e) {
            System.err.println("Failed to read all persons: " + e.getMessage());
        }

        // Test create method
        try {
            Person newPerson = new Person(4, "Alice", "Smith");
            Person createdPerson = personDao.create(newPerson);
            System.out.println("Created person: " + createdPerson);
        } catch (ResponseStatusException e) {
            System.err.println("Failed to create person: " + e.getMessage());
        }

        // Test delete method
        try {
            personDao.delete(1);
            System.out.println("Deleted person with ID 1");
        } catch (ResponseStatusException e) {
            System.err.println("Failed to delete person with ID 1: " + e.getMessage());
        }
    }
}