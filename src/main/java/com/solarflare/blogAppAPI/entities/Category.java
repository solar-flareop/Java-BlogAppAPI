package com.solarflare.blogAppAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "title", nullable = false)
    private String categoryTitle;

    @Column(name = "description", nullable = false)
    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Post>posts;
}
