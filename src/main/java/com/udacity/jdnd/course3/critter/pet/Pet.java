package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.customer.Customer;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer customer;

    private LocalDate birthDate;

    @Column(length = 1024)
    private String notes;

    @OneToMany(mappedBy = "pet")
    private Set<Schedule> schedules;

}
