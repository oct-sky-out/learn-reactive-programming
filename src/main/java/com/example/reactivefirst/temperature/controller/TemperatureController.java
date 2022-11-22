package com.example.reactivefirst.temperature.controller;

import com.example.reactivefirst.temperature.domain.Temperature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TemperatureController {
    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @GetMapping("/temperature-stream")
    public SseEmitter events() {
        SseEmitter emitter = new SseEmitter();
        clients.add(emitter);

        emitter.onTimeout(removeEmitter(emitter));
        emitter.onCompletion(removeEmitter(emitter));

        return emitter;
    }

    private Runnable removeEmitter(SseEmitter emitter) {
        return () -> clients.remove(emitter);
    }

    @Async
    @EventListener
    public void handleMessage(Temperature temperature) {
        List<SseEmitter> deadEmitters = new ArrayList<>();

        clients.forEach(sseEmitter -> {
            try {
                sseEmitter.send(temperature.getValue());
            } catch (IOException e) {
                deadEmitters.add(sseEmitter);
            }
        });

        deadEmitters.forEach(clients::remove);
    }
}
