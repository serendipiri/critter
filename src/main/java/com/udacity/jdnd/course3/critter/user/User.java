package com.udacity.jdnd.course3.critter.user;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Data
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

}
