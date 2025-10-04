package com.pokehub.pokehub_pokemon_server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PokemonResponseDTO {
    private Long id;
    private String name;
    private String type1;
    private String type2;
    private String region;
    private Long hp;
    private Long attack;
}
