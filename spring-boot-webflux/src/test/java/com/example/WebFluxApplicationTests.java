package com.example;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WebFluxApplicationTests {

	@Test
	public void contextLoads() {
	}

	private WebClient client = WebClient.create("http://localhost:8080");

	private Mono<ClientResponse> result = client.get()
			.uri("/api/hello")
			.accept(MediaType.APPLICATION_JSON)
			.exchange();

	@Test
	public void getResult() {
		// test here
		 //Assertions.assertThat(result.flatMap(res -> res.bodyToMono(Hello.class)).block()).isNotNull();
	}

}
