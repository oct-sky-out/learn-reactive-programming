package com.example.reactivefirst.reactivetest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Test3 {
    @Test
    void errorTest() {
        StepVerifier.create(Mono.error(new RuntimeException()))
            .expectSubscription()
            .expectError(RuntimeException.class)
            .verify();
    }
}
