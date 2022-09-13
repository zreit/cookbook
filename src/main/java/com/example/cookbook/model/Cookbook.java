package com.example.cookbook.model;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cookbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cookbookId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer userId;

    public Integer getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId(Integer cookbookId) {
        this.cookbookId = cookbookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
