package com.example.reactivefirst.rxjavaexam.domain;

import io.reactivex.rxjava3.core.Observable;

public class PublisherExam {
    private final Observable<String> observable = Observable.create(emitter -> {
        emitter.onNext("RxJava!");
        emitter.onComplete();
    });

    public Observable<String> getObservable() {
        return observable;
    }
}
