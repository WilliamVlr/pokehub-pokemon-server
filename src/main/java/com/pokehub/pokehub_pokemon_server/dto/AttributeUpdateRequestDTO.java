package com.pokehub.pokehub_pokemon_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AttributeUpdateRequestDTO {
    @NotBlank(message = "Attribute cannot be empty")
    private String attr;

    @Positive(message = "Amount must be a positive number")
    private int amount;
}
