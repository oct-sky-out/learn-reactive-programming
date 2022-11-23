package com.example.reactivefirst.rxjavaexam.domain;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

    @Test
    @DisplayName("Callable과 Future로 pub(Observable 생성)하여 실행도 가능하다.")
    void rxJavaUseCallableAndFuture() {
        Observable<String> helloObservable = Observable.fromCallable(() -> "hello");

        Future<String> future = Executors.newCachedThreadPool().submit(() -> " RxJava \n");
        Observable<String> rxJavaObservable = Observable.fromFuture(future);

        Observable.concat(helloObservable, rxJavaObservable)
            .forEach(System.out::print);
    }
}
