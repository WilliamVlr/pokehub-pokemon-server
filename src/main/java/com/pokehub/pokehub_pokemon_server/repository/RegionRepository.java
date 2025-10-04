package com.pokehub.pokehub_pokemon_server.repository;

import com.pokehub.pokehub_pokemon_server.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByName(String name);
}
