package com.example.cookbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AppUser {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NonNull
    @Column(unique = true, nullable = false)
    private String username;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "userId")
    private Set<Cookbook> cookbooks;

    @NonNull
    @JsonIgnore
    private String secret;
}
