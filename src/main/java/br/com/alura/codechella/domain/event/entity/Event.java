package br.com.alura.codechella.domain.event.entity;

import br.com.alura.codechella.domain.event.vo.EventCategory;
import br.com.alura.codechella.domain.event.vo.EventRegistrationData;
import br.com.alura.codechella.domain.event.vo.Address;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    private LocalDateTime date;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();

    public Event() {}

    public Event(EventRegistrationData data, List<Ticket> tickets) {
        this.name = data.name();
        this.description = data.description();
        this.category = data.category();
        this.date = data.date();
        this.address = data.address();
        this.tickets.addAll(tickets);
        this.tickets.forEach(i -> i.setEvent(this));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EventCategory getCategory() {
        return category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Address getAddress() {
        return address;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

}
