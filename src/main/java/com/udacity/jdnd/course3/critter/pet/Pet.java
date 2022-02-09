package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pet_generator")
    @SequenceGenerator(name = "seq_pet_generator", sequenceName = "seq_pet", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @ElementCollection
    private Set<PetType> type;

    @ManyToOne
    private Customer customer;

    private LocalDate birthDate;

    @Column(length = 1024)
    private String notes;

}
