package com.georgi.store.model.entity;

import com.georgi.store.model.enums.SupportedPlatforms;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private String title;
    @Enumerated(EnumType.STRING)
    private SupportedPlatforms supportedPlatforms;
    private String coverPicture;
}
