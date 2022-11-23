package com.example.reactivefirst.rxtemperature.protocol;

import com.example.reactivefirst.temperature.domain.Temperature;
import io.reactivex.rxjava3.subscribers.DefaultSubscriber;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
public class RxSseEmitter extends SseEmitter {
    static final long SSE_TIMEOUT = 30 * 60 * 1000L;
    private final DefaultSubscriber<Temperature> subscriber;


    public RxSseEmitter() {
        super(SSE_TIMEOUT);

        this.subscriber = new DefaultSubscriber<>() {
            @Override
            public void onNext(Temperature temperature) {
                try {
                    RxSseEmitter.this.send(temperature.getValue());
                } catch (IOException e) {
                    cancel();
                }
            }

            @Override
            public void onError(Throwable t) {
                cancel();
                log.error(t.getMessage());
            }

            @Override
            public void onComplete() {
                cancel();}
        };

        this.onCompletion(subscriber::onComplete);
        this.onError(subscriber::onError);
    }

    public DefaultSubscriber<Temperature> getSubscriber() {
        return subscriber;
    }
}
