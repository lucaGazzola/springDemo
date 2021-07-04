package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService
{
  private final PersonDao personDao;

  @Autowired
  public PersonService(@Qualifier("postgres") PersonDao personDao)
  {
    this.personDao = personDao;
  }

  public void addPerson(Person person)
  {
    personDao.insertPerson(person);
  }

  @Cacheable("people")
  public List<Person> getAllPeople()
  {
    return personDao.selectAllPerople();
  }

  public Optional<Person> selectPersonById(UUID id)
  {
    return personDao.selectPersonById(id);
  }

  public int deletePersonById(UUID id)
  {
    return personDao.deletePersonById(id);
  }

  public int updatePersonById(UUID id, Person newPerson)
  {
    return personDao.updatePersonById(id, newPerson);
  }
}
