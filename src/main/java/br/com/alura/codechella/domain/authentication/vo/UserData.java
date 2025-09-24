package br.com.alura.codechella.domain.authentication.vo;

import br.com.alura.codechella.domain.authentication.entity.User;

import java.time.LocalDate;

public record UserData(
        Long id,
        String name,
        String cpf,
        String email,
        LocalDate birthDate) {

    public UserData(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getBirthDate()
        );
    }

}
