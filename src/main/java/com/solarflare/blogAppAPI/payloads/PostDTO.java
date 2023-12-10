package com.solarflare.blogAppAPI.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

}
