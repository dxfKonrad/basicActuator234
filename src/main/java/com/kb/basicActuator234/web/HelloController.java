package com.kb.basicActuator234.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor

public class HelloController {
    private final ApplicationEventPublisher eventPublisher;
    private final ApplicationAvailability availability;

    @GetMapping
    public String sayHello() {
        return "hello234";
    }

    @PostMapping("/invalidate")
    public void invalidate() {
        AvailabilityChangeEvent.publish(this.eventPublisher, new Object(), LivenessState.BROKEN);
    }

    @PostMapping("/revalidate")
    public void revalidate() {
        AvailabilityChangeEvent.publish(this.eventPublisher, new Object(), LivenessState.CORRECT);
    }

    @PostMapping("/livenessStatus")
    public String livenessStatus() {
        return availability.getLivenessState().toString();
    }

}
