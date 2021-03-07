package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person
{
  private final String name;
  private final UUID id;

  public Person(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name)
  {
    this.id = id;
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public UUID getId()
  {
    return id;
  }
}
