package br.com.alura.codechella.domain.authentication.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserRegistrationData(
        @NotBlank(message = "User name is required!")
        String name,
        @NotBlank(message = "User CPF is required!")
        @CPF(message = "Invalid CPF format!")
        String cpf,
        @NotBlank(message = "User email is required!")
        @Email(message = "Invalid email format!")
        String email,
        @NotNull(message = "User birth date is required!")
        LocalDate birthDate) {
}
