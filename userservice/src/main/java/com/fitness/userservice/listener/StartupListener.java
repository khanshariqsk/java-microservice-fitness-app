package com.fitness.userservice.listener;

import com.fitness.userservice.config.app.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupListener {
    private final AppProperties appProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println("🚀 App is up and running!");
    }
}
