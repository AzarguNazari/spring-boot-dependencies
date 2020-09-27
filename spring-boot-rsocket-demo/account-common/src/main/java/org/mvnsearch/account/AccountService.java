package org.mvnsearch.account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> findById(Integer id);

    Flux<Account> findAll();

}
