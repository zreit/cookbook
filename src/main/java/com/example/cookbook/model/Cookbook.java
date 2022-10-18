package com.example.cookbook.model;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cookbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cookbookId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer userId;

}
