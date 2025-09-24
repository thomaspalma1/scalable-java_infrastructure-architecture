package br.com.alura.codechella.domain.event.vo;

import br.com.alura.codechella.domain.event.entity.Purchase;
import br.com.alura.codechella.domain.event.entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public record PurchaseData(
        Long id,
        LocalDateTime date,
        PaymentMethod paymentMethod,
        List<TicketPurchaseData> tickets) {

    public PurchaseData(Purchase purchase, List<Ticket> tickets) {
        this(
                purchase.getId(),
                purchase.getDate(),
                purchase.getPaymentMethod(),
                tickets.stream().map(TicketPurchaseData::new).toList());
    }

}
