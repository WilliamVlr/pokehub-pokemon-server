package com.pokehub.pokehub_pokemon_server.repository;

import com.pokehub.pokehub_pokemon_server.model.entity.Pokemon;
import org.springframework.data.jpa.domain.Specification;

public class PokemonSpecification {
    public static Specification<Pokemon> hasName(String name) {
        return ((root, query, criteriaBuilder) -> name == null ? null :
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<Pokemon> hasRegion(String region) {
        return (root, query, cb) -> region == null ? null :
                cb.equal(root.get("region").get("name"), region);
    }

    public static Specification<Pokemon> hasType(String type) {
        return (root, query, cb) -> type == null ? null :
                cb.or(
                        cb.equal(root.get("type1").get("name"), type),
                        cb.equal(root.get("type2").get("name"), type)
                );
    }
}
