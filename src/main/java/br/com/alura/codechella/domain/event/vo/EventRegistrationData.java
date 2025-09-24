package br.com.alura.codechella.domain.event.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record EventRegistrationData(
        @NotBlank(message = "Event name is required!")
        String name,
        @NotBlank(message = "Event description is required!")
        String description,
        @NotNull(message = "Event category is required!")
        EventCategory category,
        @NotNull(message = "Event date is required!")
        @Future(message = "Event date must be a future date!")
        LocalDateTime date,
        @NotNull(message = "Event address is required!")
        @Valid
        Address address,
        @NotNull(message = "At least one ticket type must be provided!")
        @Valid
        List<TicketRegistrationData> tickets
) {}
