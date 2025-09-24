package br.com.alura.codechella.domain.event.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PurchaseRequestData(
        @NotNull(message = "Event ID is required!")
        Long eventId,
        @NotNull(message = "Payment method is required!")
        PaymentMethod paymentMethod,
        @NotNull(message = "At least 1 ticket must be purchased!")
        @Size(message = "At least 1 ticket must be purchased!")
        List<TicketPurchaseRequestData> tickets) {
}
