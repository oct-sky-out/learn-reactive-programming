package com.example.reactivefirst.reactivetest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Test1 {

    @Test
    @DisplayName("Flux를 테스트 구독하여 요소가 정확이 있는지 검증한다.")
    void expectSubscriber() {
        StepVerifier.create(Flux.just("1", "2"))
            .expectSubscription()
            .expectNextCount(1)
            .expectNext("2")
            .verifyComplete();
    }
}
