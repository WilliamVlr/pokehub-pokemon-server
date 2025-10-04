package com.pokehub.pokehub_pokemon_server.seeder;

import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import com.pokehub.pokehub_pokemon_server.model.entity.Region;
import com.pokehub.pokehub_pokemon_server.model.entity.Type;
import com.pokehub.pokehub_pokemon_server.repository.PokemonRepository;
import com.pokehub.pokehub_pokemon_server.repository.RegionRepository;
import com.pokehub.pokehub_pokemon_server.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Configuration
@Slf4j
@RequiredArgsConstructor
@Profile("dev")
public class PokemonSeeder implements Seeder {
    private final PokemonRepository pokemonRepository;
    private final TypeRepository typeRepository;
    private final RegionRepository regionRepository;
    private final Random random = new Random();


    @Override
    public void seed() {
        if(pokemonRepository.count() == 0) {
            Faker faker = new Faker(new Locale("en"));

            List<Type> types = typeRepository.findAll();
            List<Region> regions = regionRepository.findAll();

            for (int i = 0; i < 50; i++) {
                Type type1 = types.get(random.nextInt(types.size()));
                Type type2 = null;

                if(random.nextBoolean()) {
                    type2 = types.get(random.nextInt(types.size()));
                    if (type1.equals(type2)) type2 = null;
                }
//                types.add(type1);

                Pokemon pokemon = Pokemon.builder()
                        .name(faker.pokemon().name())
                        .type1(type1)
                        .type2(type2)
                        .region(regions.get(random.nextInt(regions.size())))
                        .hp((long) faker.number().numberBetween(50, 999))
                        .attack((long) faker.number().numberBetween(1, 200))
                        .build();
                pokemonRepository.save(pokemon);
            }
            log.info("Pokemons seeded successfully (50 dummy records)");
        } else {
            log.info("Pokémons already seeded, skipping...");
        }
    }
}
