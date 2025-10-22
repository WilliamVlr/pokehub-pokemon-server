package com.pokehub.pokehub_pokemon_server.service;

import com.pokehub.pokehub_pokemon_server.dto.PokemonCreateDTO;
import com.pokehub.pokehub_pokemon_server.dto.PokemonMapper;
import com.pokehub.pokehub_pokemon_server.dto.PokemonResponseDTO;
import com.pokehub.pokehub_pokemon_server.exception.ResourceNotFoundException;
import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import com.pokehub.pokehub_pokemon_server.model.entity.Region;
import com.pokehub.pokehub_pokemon_server.model.entity.Type;
import com.pokehub.pokehub_pokemon_server.model.enums.Attribute;
import com.pokehub.pokehub_pokemon_server.repository.PokemonRepository;
import com.pokehub.pokehub_pokemon_server.repository.RegionRepository;
import com.pokehub.pokehub_pokemon_server.repository.TypeRepository;
import com.pokehub.pokehub_pokemon_server.utils.PaginationRequest;
import com.pokehub.pokehub_pokemon_server.utils.PaginationUtils;
import com.pokehub.pokehub_pokemon_server.utils.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class PokemonService {
    private final PokemonRepository repo;
    private final TypeRepository typeRepository;
    private final RegionRepository regionRepository;
    private final PokemonMapper pokemonMapper;

    public PokemonService(PokemonRepository repo, TypeRepository typeRepository, RegionRepository regionRepository, PokemonMapper pokemonMapper) {
        this.repo = repo;
        this.typeRepository = typeRepository;
        this.regionRepository = regionRepository;
        this.pokemonMapper = pokemonMapper;
    }

    //get All Pokemon
    public List<PokemonResponseDTO> getAllPokemons() {
        return repo.findAllByOrderById()
                .stream()
                .map(pokemonMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PagingResult<PokemonResponseDTO> getAllPokemonsPaged(PaginationRequest request) {
        final Pageable pageable = PaginationUtils.getPageable(request);
        final Page<Pokemon> entities = repo.findAll(pageable);
        final List<PokemonResponseDTO> dtos = entities.stream().map(pokemonMapper::toResponseDTO).toList();
        return new PagingResult<>(
                dtos,
                entities.getTotalPages(),
                entities.getTotalElements(),
                entities.getSize(),
                entities.getNumber(),
                entities.isEmpty()
        );
    }

    //get pokemon by id
    public PokemonResponseDTO getPokemonById(Long id) {
        Pokemon pokemon = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found"));
        return pokemonMapper.toResponseDTO(pokemon);
    }

    //create pokemon
    public PokemonResponseDTO createPokemon(PokemonCreateDTO dto) {
        Type type1 = typeRepository.findById(dto.getType1()).orElseThrow(() -> new ResourceNotFoundException("Type1 not found with ID: " + dto.getType1()));
        Type type2 = dto.getType2() == null ? null : typeRepository.findById(dto.getType2()).orElseThrow(() -> new ResourceNotFoundException("Type2 not found with ID: " + dto.getType2()));
        Region region = regionRepository.findById(dto.getRegion()).orElseThrow(() -> new ResourceNotFoundException("Region not found with ID: " + dto.getRegion()));

        Pokemon pokemon = new Pokemon(type1, type2, region, dto.getName(), dto.getHp(), dto.getAttack());
        return pokemonMapper.toResponseDTO(repo.save(pokemon));
    }

    //update pokemon
    public PokemonResponseDTO updatePokemon(Long id, PokemonCreateDTO dto) {
        Pokemon existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pokemon with id: " + id + " not found."));
        Type type1 = typeRepository.findById(dto.getType1()).orElseThrow(() -> new ResourceNotFoundException("Type1 not found with ID: " + dto.getType1()));
        Type type2 = dto.getType2() == null ? null : typeRepository.findById(dto.getType2()).orElseThrow(() -> new ResourceNotFoundException("Type2 not found with ID: " + dto.getType2()));
        Region region = regionRepository.findById(dto.getRegion()).orElseThrow(() -> new ResourceNotFoundException("Region not found with ID: " + dto.getRegion()));

        existing.setName(dto.getName());
        existing.setType1(type1);
        existing.setType2(type2);
        existing.setRegion(region);
        existing.setHp(dto.getHp());
        existing.setAttack(dto.getAttack());
        return pokemonMapper.toResponseDTO(repo.save(existing));
    }

    //delete pokemon
    public void deletePokemon(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Pokemon not found");
        }
        repo.deleteById(id);
    }

    public void increasePokemonAttribute(Long id, String attr, int amount) {
        Pokemon pokemon = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found"));

        Attribute attribute;

        // Ubah string menjadi enum dengan aman
        try {
            // Gunakan .toUpperCase() agar "hp", "Hp", dan "HP" semua valid
            attribute = Attribute.valueOf(attr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Atribut tidak valid: " + attr);
            return; // Keluar dari method jika input tidak valid
        }

        switch (attribute) {
            case HP:
                pokemon.setHp(pokemon.getHp() + amount);
                break;
            case ATTACK:
                pokemon.setAttack(pokemon.getAttack() + amount);
                break;
            default:
                break;
        }

        repo.save(pokemon);
    }
}
