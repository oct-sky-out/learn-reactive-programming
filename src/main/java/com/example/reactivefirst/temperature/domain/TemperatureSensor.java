package com.example.reactivefirst.temperature.domain;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TemperatureSensor {
    // ApplicationEventPublisher class는 이벤트를 시스템에 발행
    private final ApplicationEventPublisher publisher;
    private final Random random = new SecureRandom();
    // 주기적으로 시간간격마다 특정 behavior를 실행
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public TemperatureSensor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void startProcessing() {
        this.executor.schedule(this::probe, 1, TimeUnit.SECONDS);
    }

    private void probe() {
        double temp = 16 + random.nextGaussian() * 10;
        this.publisher.publishEvent(new Temperature(temp));

        this.executor.schedule(this::probe, random.nextInt(5000), TimeUnit.MILLISECONDS);
    }

}
