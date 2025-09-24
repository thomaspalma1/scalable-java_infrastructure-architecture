package br.com.alura.codechella.domain.event.service;

import br.com.alura.codechella.domain.BusinessRuleException;
import br.com.alura.codechella.domain.authentication.entity.User;
import br.com.alura.codechella.domain.event.entity.Purchase;
import br.com.alura.codechella.domain.event.entity.Ticket;
import br.com.alura.codechella.domain.event.repository.PurchaseRepository;
import br.com.alura.codechella.domain.event.repository.TicketRepository;
import br.com.alura.codechella.domain.event.vo.PurchaseData;
import br.com.alura.codechella.domain.event.vo.TicketPurchaseRequestData;
import br.com.alura.codechella.domain.event.vo.PurchaseRequestData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final TicketRepository ticketRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, TicketRepository ticketRepository) {
        this.purchaseRepository = purchaseRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<PurchaseData> listMyPurchases(User user) {
        var purchaseDataList = new ArrayList<PurchaseData>();
        var purchases = purchaseRepository.findAllByUser(user);
        purchases.forEach(purchase -> {
            var tickets = ticketRepository.findAllByPurchase(purchase);
            purchaseDataList.add(new PurchaseData(purchase, tickets));
        });
        return purchaseDataList;
    }

    public PurchaseData getPurchaseDetails(Long id) {
        var purchase = purchaseRepository.findById(id).get();
        var tickets = ticketRepository.findAllByPurchase(purchase);
        return new PurchaseData(purchase, tickets);
    }

    public PurchaseData makePurchase(PurchaseRequestData purchaseRequest, User user) {
        validateTicketAvailabilityForEvent(purchaseRequest);
        var purchase = savePurchase(purchaseRequest, user);
        var tickets = purchaseTickets(purchaseRequest, purchase);
        return new PurchaseData(purchase, tickets);
    }

    private void validateTicketAvailabilityForEvent(PurchaseRequestData purchaseRequest) {
        purchaseRequest.tickets().stream().forEach(ticket -> {
            var available = ticketRepository.hasAvailableTickets(purchaseRequest.eventId(), ticket.description(), ticket.quantity());
            if (!available) {
                throw new BusinessRuleException("Insufficient quantity for ticket type " + ticket.description());
            }
        });
    }

    private Purchase savePurchase(PurchaseRequestData purchaseRequest, User user) {
        var purchase = new Purchase(user, purchaseRequest.paymentMethod());
        this.purchaseRepository.save(purchase);
        return purchase;
    }

    private List<Ticket> purchaseTickets(PurchaseRequestData purchaseRequest, Purchase purchase) {
        var purchasedTickets = new ArrayList<Ticket>();
        var quantityByDescriptionAndType = purchaseRequest.tickets()
                .stream()
                .collect(Collectors.groupingBy(
                        TicketPurchaseRequestData::description,
                        Collectors.groupingBy(
                                TicketPurchaseRequestData::type,
                                Collectors.summingInt(TicketPurchaseRequestData::quantity))));

        quantityByDescriptionAndType.forEach((description, quantityByType) -> {
            quantityByType.forEach((type, quantity) -> {
                var availableTickets = ticketRepository.findAvailableTickets(purchaseRequest.eventId(), description, quantity);
                if (availableTickets.size() != quantity) {
                    throw new BusinessRuleException("Insufficient quantity for ticket type " + description);
                }
                availableTickets.forEach(t -> {
                    t.registerPurchase(purchase, type);
                    purchasedTickets.add(t);
                });
            });
        });

        return purchasedTickets;
    }

}
