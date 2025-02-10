package com.georgi.store.model.entity;

import com.georgi.store.model.enums.Console;
import jakarta.persistence.*;
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
@Table(name = "games")
public class Game extends BaseEntity {

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Platform> platforms;

    private String coverPicture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "game")
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
    private List<WishList> wishlists;

    public void addGameToWishlist(WishList wishlist) {
        this.wishlists.add(wishlist);
        wishlist.getGames().add(this);
    }
    public void removeGameFromWishlist(WishList wishlist) {
        this.wishlists.remove(wishlist);
        wishlist.getGames().remove(this);
    }
}
