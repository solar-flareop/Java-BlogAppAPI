package com.solarflare.blogAppAPI.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "posts")
@Getter @Setter @NoArgsConstructor @ToString
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
