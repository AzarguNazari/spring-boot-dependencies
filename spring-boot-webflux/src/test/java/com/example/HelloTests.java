package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = WebFluxApplication.class)
public class HelloTests {

    @Autowired
    WebTestClient client;

    @Test
    public void getHello() {
        client.get().uri("/api/hello")
                    .exchange().expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody().jsonPath("$.message").isEqualTo("Hello");
    }

    @Test
    public void threeNamesExists() {
        client.get().uri("/api/names")
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$[1].message").isEqualTo("Karim");
    }
}
