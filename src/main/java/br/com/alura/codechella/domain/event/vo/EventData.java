package br.com.alura.codechella.domain.event.vo;

import br.com.alura.codechella.domain.event.entity.Event;

import java.time.LocalDateTime;

public record EventData(
        Long id,
        String name,
        String description,
        LocalDateTime date,
        EventCategory category,
        Address address
) {

    public EventData(Event event) {
        this(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getDate(), event.getCategory(),
                event.getAddress()
        );
    }

}
