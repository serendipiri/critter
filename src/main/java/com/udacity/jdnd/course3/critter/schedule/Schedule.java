package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_schedule")
    @SequenceGenerator(name = "seq_schedule", sequenceName = "seq_schedule_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    private List<Employee> employeeList;

    @ManyToMany
    private List<Pet> petList;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    private LocalDate date;

}
