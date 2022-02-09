package com.udacity.jdnd.course3.critter.user;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_generator")
    @SequenceGenerator(name = "seq_user_generator", sequenceName = "seq_user", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

}
