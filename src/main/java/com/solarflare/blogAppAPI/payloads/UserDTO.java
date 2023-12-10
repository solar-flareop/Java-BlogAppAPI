package com.solarflare.blogAppAPI.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private int id;

    @NotEmpty
    @Size(min = 3, message = "Username should have min 3 chars")
    private String name;

    @Email(message = "Enter a valid email")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password should have min 3 and max 10 chars")
    private String password;

    @NotEmpty
    private String about;
}
