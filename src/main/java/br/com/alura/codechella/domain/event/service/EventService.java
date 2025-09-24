package br.com.alura.codechella.domain.event.service;

import br.com.alura.codechella.domain.BusinessRuleException;
import br.com.alura.codechella.domain.event.entity.Event;
import br.com.alura.codechella.domain.event.entity.Ticket;
import br.com.alura.codechella.domain.event.repository.EventRepository;
import br.com.alura.codechella.domain.event.vo.EventRegistrationData;
import br.com.alura.codechella.domain.event.vo.TicketRegistrationData;
import br.com.alura.codechella.domain.event.vo.EventData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventData> listUpcomingEvents() {
        var upcomingEvents = eventRepository.findAllByDateAfter(LocalDateTime.now());
        return upcomingEvents.stream().map(EventData::new).toList();
    }

    public EventData register(EventRegistrationData registrationData) {
        var eventAlreadyExists = eventRepository.existsByNameIgnoringCase(registrationData.name());
        if (eventAlreadyExists) {
            throw new BusinessRuleException("Event already registered with this name!");
        }

        var tickets = createTickets(registrationData.tickets());
        var event = new Event(registrationData, tickets);
        this.eventRepository.save(event);

        return new EventData(event);
    }

    public EventData getEventDetails(Long id) {
        var event = eventRepository.findById(id).get();
        return new EventData(event);
    }

    private List<Ticket> createTickets(List<TicketRegistrationData> ticketDataList) {
        var tickets = new ArrayList<Ticket>();

        ticketDataList.forEach(data -> {
            IntStream.range(0, data.quantity()).forEach(i -> {
                tickets.add(new Ticket(data.description(), data.price()));
            });
        });

        return tickets;
    }

}
