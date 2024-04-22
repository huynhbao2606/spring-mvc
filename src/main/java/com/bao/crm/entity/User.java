package com.bao.crm.entity;


import com.bao.crm.validation.VaildEmail;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    @NotEmpty(message = "username is required")
    private String  username;

    @Column(name = "password")
    @NotEmpty(message = "password is required")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "email is required")
    @Email(message = "Email is invalid")
    @VaildEmail
    private String email;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Column(name = "image",length = 45)
    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;
}
