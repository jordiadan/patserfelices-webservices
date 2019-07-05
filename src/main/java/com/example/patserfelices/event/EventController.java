package com.example.patserfelices.event;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    public EventController() {
    }

    @GetMapping({"/events"})
    public List<Event> getAllEvents() {
        return null;
    }
}
