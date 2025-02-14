package com.georgi.store.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
@SuperBuilder
public class Game extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Platform> platforms;

    private String coverPicture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "game", orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "game_wishlist", joinColumns = {
            @JoinColumn(
                    name = "game_id"
            )
    }, inverseJoinColumns = {
            @JoinColumn(
                    name = "wishlist_id"
            )
    }
    )
    private Set<WishList> wishlists;

    public void addWishlist(WishList wishlist) {
        this.wishlists.add(wishlist);
        wishlist.getGames().add(this);
    }
    public void removeWishlist(WishList wishlist) {
        this.wishlists.remove(wishlist);
        wishlist.getGames().remove(this);
    }

    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
        platform.getGames().add(this);
    }

    public void removePlatform(Platform platform) {
        this.platforms.remove(platform);
        platform.getGames().remove(this);
    }
}
