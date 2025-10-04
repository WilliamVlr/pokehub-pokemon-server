package com.pokehub.pokehub_pokemon_server.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "pokemons", schema = "pokehub")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type1", nullable = false)
    private Type type1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type2", nullable = true)
    private Type type2;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region", nullable = false)
    private Region region;

    @Column(nullable = false, length = 100, unique = false)
    @NotBlank(message = "Pokemon name cannot be emtpy")
    private String name;

    @Column(nullable = false)
    @Min(value = 50, message = "HP must be at least 50")
    @Max(value = 999, message = "HP cannot exceed 999")
    private Long hp;

    @Column(nullable = false)
    @Min(value = 1, message = "Attack must be at least 1")
    @Max(value = 200, message = "Attack cannot exceed 200")
    private Long attack;

    @Builder
    public Pokemon(Type type1, Type type2, Region region, String name,
                   Long hp, Long attack, Long defense, Long speed) {
        this.type1 = type1;
        this.type2 = type2;
        this.region = region;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }
}
