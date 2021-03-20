package com.example.demo;

import com.palantir.docker.compose.DockerComposeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests
{

  @RegisterExtension
  public static DockerComposeExtension docker = DockerComposeExtension.builder()
    .file("docker-compose.yml")
    .build();

  @Test
  public void contextLoads()
  {
  }
}
