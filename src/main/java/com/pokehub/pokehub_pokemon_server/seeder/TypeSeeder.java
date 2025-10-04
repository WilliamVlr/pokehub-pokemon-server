package com.pokehub.pokehub_pokemon_server.seeder;

import com.pokehub.pokehub_pokemon_server.model.entity.Type;
import com.pokehub.pokehub_pokemon_server.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@RequiredArgsConstructor
@Profile("dev")
public class TypeSeeder implements Seeder{
    private final TypeRepository typeRepository;

    @Override
    public void seed() {
        if(typeRepository.count() == 0) {
            typeRepository.save(new Type("Electric"));
            typeRepository.save(new Type("Grass"));
            typeRepository.save(new Type("Fire"));
            typeRepository.save(new Type("Water"));
            log.info("Types seeded successfullt");
        } else {
            log.info("Types already seeded, skipping....");
        }
    }
}
