package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequestMapping(value = "/api")
public class WebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxApplication.class, args);
	}

	@GetMapping(value = "/hello")
	public Mono<Hello> hello(){
		return Mono.just(new Hello("Hello"));
	}

	@GetMapping(value = "/names")
	public Flux<Hello> names(){
		return Flux.just(new Hello("Nabi"),
						new Hello("Karim"),
						new Hello("Mahmood"));
	}

	public Mono<ServerResponse> helloFunctionalWay(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
				.body(BodyInserters.fromValue("Hello, functional Spring!"));
	}

	@Bean
	public RouterFunction<ServerResponse> route(WebFluxApplication greetingHandler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/api/hello-reactive")
				.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), greetingHandler::helloFunctionalWay);
	}
}

@Data
@AllArgsConstructor
class Hello{
	private String message;
}