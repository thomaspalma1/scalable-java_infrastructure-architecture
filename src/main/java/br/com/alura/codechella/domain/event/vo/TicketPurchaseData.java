package br.com.alura.codechella.domain.event.vo;

import br.com.alura.codechella.domain.event.entity.Ticket;

import java.math.BigDecimal;

public record TicketPurchaseData(String code, String description, BigDecimal price, TicketType type) {

    public TicketPurchaseData(Ticket ticket) {
        this(ticket.getCode(), ticket.getDescription(), ticket.getPrice(), ticket.getType());
    }

}
