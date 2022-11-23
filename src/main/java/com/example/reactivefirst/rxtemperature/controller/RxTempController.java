package com.example.reactivefirst.rxtemperature.controller;

import com.example.reactivefirst.rxtemperature.domain.RxTemperatureSensor;
import com.example.reactivefirst.rxtemperature.protocol.RxSseEmitter;
import com.example.reactivefirst.temperature.domain.Temperature;
import io.reactivex.rxjava3.functions.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class RxTempController {
    private final RxTemperatureSensor temperatureSensor;

    @GetMapping("/temperature-stream2")
    public SseEmitter events() {
        RxSseEmitter emitter = new RxSseEmitter();

        temperatureSensor.getTemperatureObservable()
            .subscribe(emitter.getSubscriber());

        return emitter;
    }
}
