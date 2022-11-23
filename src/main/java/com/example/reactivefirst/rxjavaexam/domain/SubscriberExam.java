package com.example.reactivefirst.rxjavaexam.domain;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SubscriberExam {
    private final Observer<String> subscriber = new Observer<>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            if(d.isDisposed()){
                d.dispose();
            }
        }

        @Override
        public void onNext(@NonNull String s) {
            System.out.println(s);
        }

        @Override
        public void onError(Throwable t) {
            System.err.println(t);
        }

        @Override
        public void onComplete() {
            System.out.println("Done");
        }
    };

    public Observer<String> getSubscriber() {
        return subscriber;
    }
}
