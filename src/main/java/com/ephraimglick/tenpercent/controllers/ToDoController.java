package com.ephraimglick.tenpercent.controllers;

import com.ephraimglick.tenpercent.db.ToDoJDBCRepository;
import com.ephraimglick.tenpercent.models.ToDo;
import com.ephraimglick.tenpercent.models.ToDoUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.ephraimglick.tenpercent.constants.Constants.Uri.*;

@RestController
public class ToDoController implements ToDoApi {
    	WebClient webClient = WebClient.builder()
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();

	@Autowired
	private Environment env;
	@Autowired
	ToDoJDBCRepository toDoJDBCRepository;

	@GetMapping("")
	public String home() {
		return "Hello 10% Time!";
	}

	@GetMapping(SLASH_TODOS)
	public List<ToDo> getTodos() {
		return toDoJDBCRepository.list();
	}

	@PostMapping(SLASH_TODOS)
	public int postTodo(@RequestBody ToDo inputToDo) {
		return toDoJDBCRepository.create(inputToDo);
	}

	// Since this is a patch, we need to remove null fields from the JSON in call to jsonplaceholder. Use Jackson?
	// Then won't need to use the ToDoUpdate object
	@PatchMapping(SLASH_TODOS_SLASH_TODO_ID)
	public int patchTodo(@RequestBody ToDoUpdate toDoUpdate, @PathVariable String todoId) {
		return toDoJDBCRepository.update(todoId, toDoUpdate);
	}

	@DeleteMapping(SLASH_TODOS_SLASH_TODO_ID)
	public int deleteTodo(@PathVariable String todoId) {
		return toDoJDBCRepository.delete(todoId);
	}

	@GetMapping(SLASH_TODOS_SLASH_TODO_ID)
	public ToDo getTodo(@PathVariable String todoId) {
		return toDoJDBCRepository.get(todoId);
	}

	@GetMapping(SLASH_SECRETS)
	public String getSecrets() {
		String PASSWORD = env.getProperty("PASSWORD");
		String USERNAME = env.getProperty("USERNAME");
		return "username: " + USERNAME + "; password: " + PASSWORD;
	}

	@GetMapping(SLASH_FAKE_TODOS)
	public List<ToDo> getFakeTodos() {
		return webClient.get().uri("https://jsonplaceholder.typicode.com/todos").retrieve().bodyToMono(new ParameterizedTypeReference<List<ToDo>>() {
		}).block();
	}
}
