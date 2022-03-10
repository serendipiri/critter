package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.employee.EmployeeSkill;
import com.udacity.jdnd.course3.critter.pet.Pet;
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
    @JoinTable(name = "schedule_employee",
        joinColumns = @JoinColumn(name = "schedule_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employeeList;

    @ManyToMany
    @JoinTable(name = "schedule_pet",
        joinColumns = @JoinColumn(name = "schedule_id"),
        inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> petList;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    private LocalDate date;

}
