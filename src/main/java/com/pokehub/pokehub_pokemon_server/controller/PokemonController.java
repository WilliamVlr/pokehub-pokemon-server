package com.pokehub.pokehub_pokemon_server.controller;

import com.pokehub.pokehub_pokemon_server.dto.PokemonCreateDTO;
import com.pokehub.pokehub_pokemon_server.dto.PokemonMapper;
import com.pokehub.pokehub_pokemon_server.dto.PokemonResponseDTO;
import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import com.pokehub.pokehub_pokemon_server.service.PokemonService;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/pokemons")
public class PokemonController {
    private final PokemonService pokemonService;
    private final PokemonMapper pokemonMapper;

    public PokemonController(PokemonService pokemonService, PokemonMapper pokemonMapper) {
        this.pokemonService = pokemonService;
        this.pokemonMapper = pokemonMapper;
    }

    @GetMapping
    public ResponseEntity<List<PokemonResponseDTO>> getAll() {
        return ResponseEntity.ok(pokemonService.getAllPokemons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> getById(@PathVariable Long id) {
        PokemonResponseDTO pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping
    public ResponseEntity<PokemonResponseDTO> create(@RequestBody @Valid PokemonCreateDTO dto) {
        PokemonResponseDTO created = pokemonService.createPokemon(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PokemonCreateDTO pokemon) {
        return ResponseEntity.ok(pokemonService.updatePokemon(id, pokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }
}
