package com.solarflare.blogAppAPI.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private int categoryId;

    @NotEmpty
    @Size(min = 3, message = "Title should have min 3 chars")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 3, message = "Description should have min 3 chars")
    private String categoryDescription;
}

