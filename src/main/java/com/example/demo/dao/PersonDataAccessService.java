package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao
{

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDataAccessService(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override

  public void insertPerson(UUID id, Person person)
  {
    jdbcTemplate.update("INSERT INTO person (id, name) VALUES (?, ?)", id, person.getName());
  }

  @Override
  public List<Person> selectAllPerople()
  {
    RowMapper<Person> tRowMapper = (resultSet, i) ->
      new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"));
    return jdbcTemplate.query("SELECT id, name FROM person", tRowMapper);
  }

  @Override
  public int deletePersonById(UUID id)
  {
    return 0;
  }

  @Override
  public int updatePersonById(UUID id, Person person)
  {
    return 0;
  }

  @Override
  public Optional<Person> selectPersonById(UUID id)
  {
    String sql = "SELECT id, name FROM person WHERE ?";
    RowMapper<Person> tRowMapper = (resultSet, i) ->
      new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"));
    Person person = jdbcTemplate.queryForObject(sql, tRowMapper, id);
    return Optional.ofNullable(person);
  }
}
