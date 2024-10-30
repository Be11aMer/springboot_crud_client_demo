package de.szut.springboot_crud_client_demo.dao;

import java.util.HashMap;
import java.util.List;

import de.szut.springboot_crud_client_demo.model.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class PersonDao {

    private final HashMap<Integer, Person> personList = new HashMap<>();
    private static final  MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URI = "http://localhost:8081/springboot-crud-service/person";


    public Person read(int id) {
        HttpHeaders headers = createHttpHeaders();
        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
        String uri = URI + "/" + id;
        ResponseEntity<Person> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Person.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new ResponseStatusException(statusCode, "Error reading person data. Please try again later.");
        }
    }

    public Person[] readAll() {
        HttpHeaders headers = createHttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Person[]> responseEntity = restTemplate.exchange(URI, HttpMethod.GET, requestEntity, Person[].class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful() && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        } else {
            throw new ResponseStatusException(statusCode, "Error reading person data. Please try again later.");
        }
    }

    public Person create(Person person) {
        HttpHeaders headers = createHttpHeaders();
        HttpEntity<Person> requestEntity = new HttpEntity<>(person,headers);
        ResponseEntity<Person> responseEntity = restTemplate.postForEntity(URI, requestEntity, Person.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return responseEntity.getBody();
        }else {
            System.out.println("Error creating person data. Please try again later.");
            return null;
        }
    }

    public void delete(int id) {
        HttpHeaders headers = createHttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        String uri = URI + "/" + id;
        ResponseEntity<Void> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, Void.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (!statusCode.is2xxSuccessful()) {
            throw new ResponseStatusException(statusCode, "Error deleting person data. Please try again later.");
        }
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MEDIA_TYPE));
        headers.setContentType(MEDIA_TYPE);
        return headers;
    }


}
