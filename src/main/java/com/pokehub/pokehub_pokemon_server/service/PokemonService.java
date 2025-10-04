package com.pokehub.pokehub_pokemon_server.service;

import com.pokehub.pokehub_pokemon_server.exception.ResourceNotFoundException;
import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import com.pokehub.pokehub_pokemon_server.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PokemonService {
    private final PokemonRepository repo;

    public PokemonService(PokemonRepository repo) {
        this.repo = repo;
    }

    //get All Pokemons
    public List<Pokemon> getAllPokemons() {
        return repo.findAll();
    }

    //get pokemon by id
    public Pokemon getPokemonById(Long id) {
        return repo.findById(id).orElse(null);
    }

    //create pokemon
    public Pokemon createPokemon(Pokemon pokemon) {
        return repo.save(pokemon);
    }

    //update pokemon
    public Pokemon updatePokemon(Long id, Pokemon newPokemon) {
        Pokemon existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pokemon with id: " + id + " not found."));

        existing.setName(newPokemon.getName());
        existing.setType1(newPokemon.getType1());
        existing.setType2(newPokemon.getType2());
        existing.setHp(newPokemon.getHp());
        existing.setAttack(newPokemon.getAttack());
        return repo.save(existing);
    }

    //delete pokemon
    public void deletePokemon(Long id) {
        repo.deleteById(id);
    }
}
