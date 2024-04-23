package com.bao.crm.dto;


import com.bao.crm.validation.VaildEmail;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private int id;

    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Email is required")
    @VaildEmail
    private String email;

    private MultipartFile image;

    private String imageUrl;

}
