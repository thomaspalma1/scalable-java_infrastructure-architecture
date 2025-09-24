package br.com.alura.codechella.domain.event.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketPurchaseRequestData(
        @NotBlank(message = "Ticket description is required!")
        String description,
        @NotNull(message = "Ticket quantity is required!")
        @Min(value = 1, message = "Minimum ticket quantity must be 1!")
        Integer quantity,
        @NotNull(message = "Ticket type is required!")
        TicketType type) {
}
