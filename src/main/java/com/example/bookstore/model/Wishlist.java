package com.example.bookstore.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wishlists")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private TempUser user;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WishlistItem> items;

    // Getters and setters
    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public TempUser getUser() {
        return user;
    }

    public void setUser(TempUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WishlistItem> getItems() {
        return items;
    }

    public void setItems(Set<WishlistItem> items) {
        this.items = items;
    }
}
