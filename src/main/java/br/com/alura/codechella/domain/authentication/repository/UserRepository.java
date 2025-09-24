package br.com.alura.codechella.domain.authentication.repository;

import br.com.alura.codechella.domain.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.profiles WHERE u.email = :email")
    User findByEmail(String email);

    boolean existsByEmailOrCpf(String email, String cpf);

}
