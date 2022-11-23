package com.example.reactivefirst.rxjavaexam.domain;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            log.debug(s);
        }

        @Override
        public void onError(Throwable t) {
            log.error("error : {}", t.getMessage());
        }

        @Override
        public void onComplete() {
            log.debug("Done");
        }
    };

    public Observer<String> getSubscriber() {
        return subscriber;
    }
}
