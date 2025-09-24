package br.com.alura.codechella.controller;

import br.com.alura.codechella.domain.event.service.EventService;
import br.com.alura.codechella.domain.event.vo.EventRegistrationData;
import br.com.alura.codechella.domain.event.vo.EventData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EventData>> listUpcomingEvents() {
        var upcomingEvents = service.listUpcomingEvents();
        return ResponseEntity.ok(upcomingEvents);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventData> getDetails(@PathVariable Long id) {
        var event = service.getEventDetails(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EventData> register(@RequestBody @Valid EventRegistrationData registrationData, UriComponentsBuilder uriBuilder) {
        var eventData = service.register(registrationData);
        var uri = uriBuilder.path("events/{id}").buildAndExpand(eventData.id()).toUri();
        return ResponseEntity.created(uri).body(eventData);
    }

}
