package com.udacity.jdnd.course3.critter.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e" +
            " join e.skills s" +
            " where :availableDay member of e.daysAvailable " +
            " and :count = ( select count(*) from s in (:skills) ) "  )
    List<Employee> getAvailableEmployeeBySkills(DayOfWeek availableDay, Set<EmployeeSkill> skills, int count);

}
