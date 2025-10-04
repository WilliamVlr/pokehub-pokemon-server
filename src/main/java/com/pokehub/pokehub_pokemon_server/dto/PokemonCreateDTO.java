package com.pokehub.pokehub_pokemon_server.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PokemonCreateDTO {
    @NotBlank(message = "Pokemon name cannot be empty")
    private String name;

    @NotNull(message = "Type1 ID is required")
    private Long type1;

    private Long type2;

    @NotNull(message = "Region ID is required")
    private Long region;

    @NotNull
    @Min(50)
    @Max(999)
    private Long hp;

    @NotNull
    @Min(1)
    @Max(200)
    private Long attack;
}
