package br.com.alura.codechella.domain.event.repository;

import br.com.alura.codechella.domain.event.entity.Purchase;
import br.com.alura.codechella.domain.event.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT COUNT(t) >= :quantity FROM Ticket t WHERE t.event.id = :eventId AND t.description = :description")
    Boolean hasAvailableTickets(Long eventId, String description, Integer quantity);

    @Query("SELECT t FROM Ticket t WHERE t.event.id = :eventId AND t.description = :description AND t.purchase IS NULL ORDER BY t.id LIMIT :quantity")
    List<Ticket> findAvailableTickets(Long eventId, String description, Integer quantity);

    List<Ticket> findAllByPurchase(Purchase purchase);

}
