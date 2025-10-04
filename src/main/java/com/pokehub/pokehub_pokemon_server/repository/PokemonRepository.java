package com.pokehub.pokehub_pokemon_server.repository;

import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>, JpaSpecificationExecutor<Pokemon> {
    List<Pokemon> findByNameContainingIgnoreCase(String name);

    List<Pokemon> findByType1_Name(String type1);
    List<Pokemon> findByType2_Name(String type2);

    List<Pokemon> findByRegion_Name(String region);
}
