package com.georgi.store.model.dto.gameDto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameResponse {
    private String id;
    private String name;
    private Set<String> platforms;
    private String imageUrl; // the CDN url
}
