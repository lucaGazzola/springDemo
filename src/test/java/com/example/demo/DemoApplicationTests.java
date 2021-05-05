package com.example.demo;

import com.example.demo.api.PersonController;
import com.palantir.docker.compose.DockerComposeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests
{

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @RegisterExtension
  public static DockerComposeExtension docker = DockerComposeExtension.builder()
    .file("docker-compose.yml")
    .build();

  @Autowired
  PersonController personController;

  @Test
  public void simpleRequest()
  {
    assertThat(personController).isNotNull();
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/person", String.class))
      .contains("[]");
  }
}
