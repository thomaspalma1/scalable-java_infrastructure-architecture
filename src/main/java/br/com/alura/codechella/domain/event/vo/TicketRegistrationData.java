package br.com.alura.codechella.domain.event.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TicketRegistrationData(
        @NotBlank(message = "Ticket description is required!")
        String description,
        @NotNull(message = "Ticket price is required!")
        BigDecimal price,
        @NotNull(message = "Ticket quantity is required!")
        @Min(value = 1, message = "Minimum ticket quantity must be 1!")
        Integer quantity
) {}
