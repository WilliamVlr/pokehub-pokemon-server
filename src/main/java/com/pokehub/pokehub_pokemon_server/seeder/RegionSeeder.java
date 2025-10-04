package com.pokehub.pokehub_pokemon_server.seeder;

import com.pokehub.pokehub_pokemon_server.model.entity.Region;
import com.pokehub.pokehub_pokemon_server.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class RegionSeeder implements Seeder{
    private final RegionRepository regionRepository;


    @Override
    public void seed() {
        if(regionRepository.count() == 0) {
            regionRepository.save(new Region("Kanto"));
            regionRepository.save(new Region("Johto"));
            regionRepository.save(new Region("Sinnoh"));
            log.info("Regions seeded successfully");
        } else {
            log.info("Regions already seeded, skipping...");
        }
    }
}
