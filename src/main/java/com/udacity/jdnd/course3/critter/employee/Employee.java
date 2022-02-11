package com.udacity.jdnd.course3.critter.employee;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    @OneToMany(mappedBy = "employee")
    private Set<Schedule> schedules;

}
