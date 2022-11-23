package com.example.reactivefirst.rxjavaexam;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class AsyncRxJavaTest {
    @Test
    void asyncRxjavaExample() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(i -> System.out.println(i));

        Thread.sleep(10000);
    }
}
