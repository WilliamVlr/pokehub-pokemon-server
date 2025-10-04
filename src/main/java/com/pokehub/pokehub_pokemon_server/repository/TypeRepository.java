package com.pokehub.pokehub_pokemon_server.repository;

import com.pokehub.pokehub_pokemon_server.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    boolean existsByName(String name);
}
