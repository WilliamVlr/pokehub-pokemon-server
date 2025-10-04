package com.pokehub.pokehub_pokemon_server.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pokemons", schema = "pokehub")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type1", nullable = false)
    private Type type1;

    @ManyToOne
    @JoinColumn(name = "type2", nullable = true)
    private Type type2;

    @ManyToOne
    @JoinColumn(name = "region", nullable = false)
    private Region region;

    private String name;
    private Long hp;
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
