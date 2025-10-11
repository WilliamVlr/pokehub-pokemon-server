package com.pokehub.pokehub_pokemon_server.controller;

import com.pokehub.pokehub_pokemon_server.dto.PokemonCreateDTO;
import com.pokehub.pokehub_pokemon_server.dto.PokemonMapper;
import com.pokehub.pokehub_pokemon_server.dto.PokemonResponseDTO;
import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import com.pokehub.pokehub_pokemon_server.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@CrossOrigin("*")
@RequestMapping("/api/pokemons")
@Tag(name = "Pokemon Management", description = "APIs for managing Pokemons")
public class PokemonController {
    private final PokemonService pokemonService;
    private final PokemonMapper pokemonMapper;

    public PokemonController(PokemonService pokemonService, PokemonMapper pokemonMapper) {
        this.pokemonService = pokemonService;
        this.pokemonMapper = pokemonMapper;
    }

    @Operation(summary = "Get all pokemons data", description = "Fetch all your pokemons data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokemons retrieved successfully",
                content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<PokemonResponseDTO>> getAll() {
        return ResponseEntity.ok(pokemonService.getAllPokemons());
    }

    @Operation(summary = "Get a pokemon data", description = "Fetch a pokemon data based on its ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Pokemon retrieved successfully.",
                content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pokemon not found",
                content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> getById(@PathVariable Long id) {
        PokemonResponseDTO pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @Operation(summary = "Add new pokemon", description = "Create new pokemon data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokemon added successfully.",
                content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data, failed to add pokemon.",
                content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PokemonResponseDTO> create(@RequestBody @Valid PokemonCreateDTO dto) {
        PokemonResponseDTO created = pokemonService.createPokemon(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update an existing pokemon", description = "Update pokemon data based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokemon updated successfully.",
                    content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pokemon not found.",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PokemonCreateDTO pokemon) {
        return ResponseEntity.ok(pokemonService.updatePokemon(id, pokemon));
    }

    @Operation(summary = "Delete a pokemon", description = "Delete a pokemon data based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pokemon deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Pokemon not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }
}
