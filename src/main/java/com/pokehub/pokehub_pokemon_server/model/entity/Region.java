package com.pokehub.pokehub_pokemon_server.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "regions", schema = "pokehub")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder
    public Region(String name) {
        this.name = name;
    }
}
