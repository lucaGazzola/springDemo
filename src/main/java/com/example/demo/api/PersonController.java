package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RequestMapping("api/v1/person")
@RestController
public class PersonController
{
  Logger logger = LoggerFactory.getLogger(PersonController.class);
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService)
  {
    this.personService = personService;
  }

  @PostMapping
  public void addPerson(@RequestBody Person person)
  {
    personService.addPerson(person);
  }

  @GetMapping
  public List<Person> getAllPeople()
  {
    try
    {
      return getAllPeopleAsync().get();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
      logger.error("Thread getting all people interrupted.");
    }
    catch (ExecutionException e)
    {
      e.printStackTrace();
      logger.error("Errors while getting all people.");
    }
    return null;
  }

  @GetMapping(path = "{id}")
  public Person selectPersonById(@PathVariable("id") UUID id)
  {
    return personService.selectPersonById(id).orElse(null);
  }

  @DeleteMapping(path = "{id}")
  public void deletePersonById(@PathVariable("id") UUID id)
  {
    personService.deletePersonById(id);
  }

  @PutMapping(path = "{id}")
  public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person)
  {
    personService.updatePersonById(id, person);
  }

  public Future<List<Person>> getAllPeopleAsync() throws InterruptedException
  {
    return CompletableFuture.supplyAsync(personService::getAllPeople);
  }
}
