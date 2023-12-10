package com.solarflare.blogAppAPI.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name",nullable = false)
    private String name;
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Post>posts;

}
