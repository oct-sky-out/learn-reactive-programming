package com.example.reactivefirst.rxjavaexam.domain;

import io.reactivex.rxjava3.core.Observer;
import org.junit.jupiter.api.Test;

class PubSubTest {

    @Test
    void pubSubTest() {
        Observer<String> observer = new SubscriberExam().getSubscriber();

        new PublisherExam().getObservable().subscribe(observer);
    }
}
