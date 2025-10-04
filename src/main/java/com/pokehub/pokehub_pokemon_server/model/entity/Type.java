package com.pokehub.pokehub_pokemon_server.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "types", schema = "pokehub")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder
    public Type(String name) {
        this.name = name;
    }
}
