package com.ephraimglick.tenpercent.controllers;

import com.ephraimglick.tenpercent.models.ToDo;
import com.ephraimglick.tenpercent.models.ToDoUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ephraimglick.tenpercent.constants.Constants.Uri.*;

@RestController
public class ToDoController implements ToDoApi {
    	WebClient webClient = WebClient.builder()
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();

	@Autowired
	private Environment env;

	@GetMapping("")
	public String home() {
		return "Hello 10% Time!";
	}

	@GetMapping(SLASH_TODOS)
	public List<ToDo> getTodos() {
		return webClient.get().uri("https://jsonplaceholder.typicode.com/todos").retrieve().bodyToMono(new ParameterizedTypeReference<List<ToDo>>() {
		}).block();
	}

	@PostMapping(SLASH_TODOS)
	public ToDo postTodo(@RequestBody ToDo inputToDo) {
		return webClient.post().uri("https://jsonplaceholder.typicode.com/todos").body(Mono.just(inputToDo), ToDo.class).retrieve().bodyToMono(ToDo.class).block();
	}

	// Since this is a patch, we need to remove null fields from the JSON in call to jsonplaceholder. Use Jackson?
	// Then won't need to use the ToDoUpdate object
	@PatchMapping(SLASH_TODOS_SLASH_TODO_ID)
	public ToDo patchTodo(@RequestBody ToDoUpdate inputToDoUpdate, @PathVariable String todoId) {
		return webClient.patch().uri("https://jsonplaceholder.typicode.com/todos/" + todoId).body(Mono.just(inputToDoUpdate), ToDo.class).retrieve().bodyToMono(ToDo.class).block();
	}

	@DeleteMapping(SLASH_TODOS_SLASH_TODO_ID)
	public Mono<Void> deleteTodo(@PathVariable String todoId) {
		return webClient.delete().uri("https://jsonplaceholder.typicode.com/todos/" + todoId).retrieve().bodyToMono(Void.class);
	}

	@GetMapping(SLASH_TODOS_SLASH_TODO_ID)
	public ToDo getTodo(@PathVariable String todoId) {
		return webClient.get().uri("https://jsonplaceholder.typicode.com/todos/" + todoId).retrieve().bodyToMono(ToDo.class).block();
	}

	@GetMapping(SLASH_SECRETS)
	public String getSecrets() {
		String PASSWORD = env.getProperty("PASSWORD");
		String USERNAME = env.getProperty("USERNAME");
		return "username: " + USERNAME + "; password: " + PASSWORD;
	}
}
