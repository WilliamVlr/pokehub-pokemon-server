package com.pokehub.pokehub_pokemon_server.seeder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DatabaseSeeder implements CommandLineRunner {

    private final RegionSeeder regionSeeder;
    private final TypeSeeder typeSeeder;
    private final PokemonSeeder pokemonSeeder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting database seeding...");

        // Seed Regions
        log.info("Seeding Regions...");
        regionSeeder.seed();

        // Seed Types
        log.info("Seeding Types...");
        typeSeeder.seed();

        // Seed Pokemons
        log.info("Seeding Pokemons...");
        pokemonSeeder.seed();

        log.info("Database seeding completed.");
    }
}
