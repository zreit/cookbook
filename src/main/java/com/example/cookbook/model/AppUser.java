package com.example.cookbook.model;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "userId")
    private Set<Cookbook> cookbooks;

    public Integer getUserid() {
        return userId;
    }

    public void setUserid(Integer userid) {
        this.userId = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    //@JSONIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Cookbook> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks(Set<Cookbook> cookbooks) {
        this.cookbooks = cookbooks;
    }
}
