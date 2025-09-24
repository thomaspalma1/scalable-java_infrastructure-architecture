package br.com.alura.codechella.domain.authentication.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(
        @NotBlank(message = "Email is required for authentication!")
        @Email(message = "Invalid email format!")
        String email,
        @NotBlank(message = "Password is required for authentication!")
        String password) {}
