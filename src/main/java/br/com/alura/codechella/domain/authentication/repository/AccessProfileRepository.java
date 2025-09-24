package br.com.alura.codechella.domain.authentication.repository;

import br.com.alura.codechella.domain.authentication.entity.AccessProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessProfileRepository extends JpaRepository<AccessProfile, Long> {

    AccessProfile findByName(String name);

}
