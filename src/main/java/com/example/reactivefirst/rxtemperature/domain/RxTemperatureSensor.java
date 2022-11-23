package com.example.reactivefirst.rxtemperature.domain;

import com.example.reactivefirst.temperature.domain.Temperature;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class RxTemperatureSensor {
    private final Random random = new SecureRandom();

    /**
     * RxJava 3.x.y 버전 에서는 Flowable이 Reactive Stream을 지원한다.
    * @Link https://seosh817.tistory.com/2
    **/
    private final Flowable<Temperature> temperatureObservable =
        Flowable.range(0, Integer.MAX_VALUE)
            .concatMap(i -> Flowable.just(i)
                .delay(random.nextInt(5000), TimeUnit.MILLISECONDS)
                .map(integer -> this.probe()))
            .publish()
            .refCount();

    private Temperature probe() {
        double tempValue = 16 + (random.nextGaussian() * 10);

        return new Temperature(tempValue);
    }

    public Flowable<Temperature> getTemperatureObservable() {
        return temperatureObservable;
    }
}
