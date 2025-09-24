package br.com.alura.codechella.domain.event.repository;

import br.com.alura.codechella.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByDateAfter(LocalDateTime date);

    Boolean existsByNameIgnoringCase(String name);

}
