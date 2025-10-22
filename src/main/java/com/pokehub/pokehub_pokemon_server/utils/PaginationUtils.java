package com.pokehub.pokehub_pokemon_server.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationUtils {
    public static Pageable getPageable(PaginationRequest request) {
        return PageRequest.of(request.getPage(), request.getSize(), request.getDirection(), request.getSortField());
    }
}
