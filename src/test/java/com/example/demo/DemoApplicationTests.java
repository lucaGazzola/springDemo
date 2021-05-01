package com.example.demo;

import com.example.demo.api.PersonController;
import com.palantir.docker.compose.DockerComposeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DemoApplicationTests
{

  @RegisterExtension
  public static DockerComposeExtension docker = DockerComposeExtension.builder()
    .file("docker-compose.yml")
    .build();

  @Autowired
  PersonController personController;

  @Test
  public void contextLoads()
  {
    assertThat(personController).isNotNull();
  }
}
