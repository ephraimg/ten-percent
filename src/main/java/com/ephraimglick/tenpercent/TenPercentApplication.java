package com.ephraimglick.tenpercent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

class ToDoUpdate {
	String title;
	Boolean completed;

	public String getTitle() {
		return title;
	}

	public Boolean getCompleted() {
		return completed;
	}
}

class ToDo {
	Integer userId;
	Integer id;
	String title;
	Boolean completed;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
}

@SpringBootApplication
@RestController
public class TenPercentApplication {

	WebClient webClient = WebClient.builder()
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.build();

	@GetMapping ("/")
	public String home() {
		return "Hello 10% Time!";
	}

	@GetMapping("/todos")
	public List<ToDo> getTodos() {
		List<ToDo> result = webClient.get().uri("https://jsonplaceholder.typicode.com/todos").retrieve().bodyToMono(new ParameterizedTypeReference<List<ToDo>>() {
		}).block();
		return result;
	}

	@PostMapping("/todos")
	public ToDo postTodo(@RequestBody ToDo inputToDo) {
		ToDo result = webClient.post().uri("https://jsonplaceholder.typicode.com/todos").body(Mono.just(inputToDo), ToDo.class).retrieve().bodyToMono(ToDo.class).block();
		return result;
	}

	// Since this is a patch, we need to remove null fields from the JSON in call to jsonplaceholder. Use Jackson?
	// Then won't need to use the ToDoUpdate object
	@PatchMapping("/todos/{todoId}")
	public ToDo patchTodo(@RequestBody ToDoUpdate inputToDoUpdate, @PathVariable String todoId) {
		ToDo result = webClient.patch().uri("https://jsonplaceholder.typicode.com/todos/" + todoId).body(Mono.just(inputToDoUpdate), ToDo.class).retrieve().bodyToMono(ToDo.class).block();
		return result;
	}

	@DeleteMapping("/todos/{todoId}")
	public Mono<Void> deleteTodo(@PathVariable String todoId) {
		Mono<Void> result = webClient.delete().uri("https://jsonplaceholder.typicode.com/todos/" + todoId).retrieve().bodyToMono(Void.class);
		return result;
	}

	@GetMapping("/todos/{todoId}")
	public ToDo getTodo(@PathVariable String todoId) {
		ToDo result = webClient.get().uri("https://jsonplaceholder.typicode.com/todos/" + todoId).retrieve().bodyToMono(ToDo.class).block();
		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(TenPercentApplication.class, args);
	}

}
