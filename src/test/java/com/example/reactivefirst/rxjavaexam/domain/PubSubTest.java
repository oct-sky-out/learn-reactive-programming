package com.example.reactivefirst.rxjavaexam.domain;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PubSubTest {

    @Test
    @DisplayName("Observer 객체를 직접 정의하여 구독하는 법")
    void pubSubTest() {
        Observer<String> observer = new SubscriberExam().getSubscriber();

        new PublisherExam().getObservable().subscribe(observer);
    }

    @Test
    @DisplayName("람다로 정의하여 Observable, Observer 모두 만드는 법")
    void pubSubTest2() {
        Observable.create(
            emitter -> {
                emitter.onNext("hello RxJava");
                emitter.onComplete();
            }
        ).subscribe(
            System.out::println,
            System.err::println,
            () -> System.out.println("done")
        );
    }
}
