package com.pokehub.pokehub_pokemon_server.dto;

import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import com.pokehub.pokehub_pokemon_server.model.entity.Region;
import com.pokehub.pokehub_pokemon_server.model.entity.Type;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonMapper {
    public PokemonResponseDTO toResponseDTO(Pokemon pokemon) {
        if (pokemon == null) return null;

        PokemonResponseDTO dto = new PokemonResponseDTO();
        dto.setId(pokemon.getId());
        dto.setName(pokemon.getName());
        dto.setHp(pokemon.getHp());
        dto.setAttack(pokemon.getAttack());

        dto.setType1(pokemon.getType1() != null ? pokemon.getType1().getName() : null);
        dto.setType2(pokemon.getType2() != null ? pokemon.getType2().getName() : null);
        dto.setRegion(pokemon.getRegion() != null ? pokemon.getRegion().getName() : null);

        return dto;
    }

    public TypeDTO toTypeDTO(Type type) {
        if(type == null) return null;
        TypeDTO dto = new TypeDTO();
        dto.setId(type.getId());
        dto.setName(type.getName());
        return dto;
    }

    public RegionDTO toRegionRegionDTO(Region reg) {
        if(reg == null) return null;
        RegionDTO dto = new RegionDTO();
        dto.setId(reg.getId());
        dto.setName(reg.getName());
        return dto;
    }
}
