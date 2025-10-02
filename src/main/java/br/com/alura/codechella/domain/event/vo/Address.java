package br.com.alura.codechella.domain.event.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Embeddable
public record Address(
        @NotBlank(message = "City is required!")
        String city,
        @NotBlank(message = "State is required!")
        String state,
        @NotBlank(message = "Street is required!")
        String street,
        @NotBlank(message = "Neighborhood is required!")
        String neighborhood,
        @NotBlank(message = "ZIP code is required!")
        String zipCode,
        String number,
        String complement
) implements Serializable {}
