package com.example.demo.acceptance;

import com.palantir.docker.compose.DockerComposeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationAcceptanceTestMockMVC
{

  @Autowired
  private MockMvc mockMvc;

  @RegisterExtension
  public static DockerComposeExtension docker = DockerComposeExtension.builder()
    .file("docker-compose.yml")
    .build();

  @Test
  public void emptyPeopleList() throws Exception
  {
    this.mockMvc.perform(get("/api/v1/person")).andDo(print()).andExpect(status().isOk())
      .andExpect(content().string(containsString("[]")));
  }
}