package com.upao.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddTaskAndRetrieveTask(){
		ObjectMapper mapper = new ObjectMapper();

		Task task1 = new Task("Task 1");
		Task task2 = new Task("Task 2");

		//Agregar Task via Rest API
		restTemplate.postForEntity("http://localhost:" + port + "/api/task", task1, Void.class);
		restTemplate.postForEntity("http://localhost:" + port + "/api/task", task2, Void.class);

		//Recuperar Task via Rest API
		JsonNode tasksnode = restTemplate.getForObject("http://localhost:" + port + "/api/task", JsonNode.class);

		List<Task> tasks = mapper.convertValue(
				tasksnode,
				new TypeReference<List<Task>>(){}
		);

		assertEquals(2, tasks.size());
		assertEquals("Task 1", tasks.get(0).getName());
		assertEquals("Task 2", tasks.get(1).getName());
	}
}
