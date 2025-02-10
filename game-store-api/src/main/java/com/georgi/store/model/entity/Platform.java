package com.georgi.store.model.entity;

import com.georgi.store.model.enums.Console;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Platform extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Console console;

    @ManyToMany(mappedBy = "platforms")
    List<Game> games;
}
