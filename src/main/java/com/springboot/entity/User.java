package com.springboot.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)//whenever we load user entity along with that it will load its roles
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns=@JoinColumn(name = "role_id",referencedColumnName = "id"))

    private Set<Role> roles;
}
