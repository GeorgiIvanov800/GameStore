package com.georgi.store.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WishList extends BaseEntity {

    private String name;

    @OneToOne(mappedBy = "wishList")
    private User user;

    @ManyToMany(mappedBy = "wishlists", fetch = FetchType.EAGER)
    private Set<Game> games;
}
