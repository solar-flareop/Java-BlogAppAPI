package com.solarflare.blogAppAPI.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private int postId;

    @NotEmpty
    @Size(min = 3, message = "Title should have min 3 chars")
    private String title;

    @NotEmpty
    @Size(min = 3,max = 10000,message = "Post can have min 3 and max 10000 words")
    private String content;

    private String imageName;
    private Date addedDate;

    //If simple cat and user is taken req goes into infinity due to mapping in entities
    private CategoryDTO category;
    private UserDTO user;

}

//The response one post is created
//returning raw post or postDto with Category cat or User user return the list in those entities as well which leads
//to infinity

   /*{
        "postId": 1,
        "title": "What is java?",
        "content": "A programming language used in industry.",
        "imageName": "default.png",
        "addedDate": "2023-12-10T18:52:35.013+00:00",
        "category": {
        "categoryId": 1,
        "categoryTitle": "Coding Language",
        "categoryDescription": " Coding Language is very important"
        },
        "user": {
        "id": 1,
        "name": "Suraj Pattade",
        "email": "suraj@gmail.com",
        "password": "test123",
        "about": "I am a professional JAVA Developer."
        }
     }*/
