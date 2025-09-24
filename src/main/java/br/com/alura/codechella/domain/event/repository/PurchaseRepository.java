package br.com.alura.codechella.domain.event.repository;

import br.com.alura.codechella.domain.authentication.entity.User;
import br.com.alura.codechella.domain.event.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByUser(User user);

}
