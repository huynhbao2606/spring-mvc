package com.bao.crm.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId",referencedColumnName = "id")
    private Role role;
}
