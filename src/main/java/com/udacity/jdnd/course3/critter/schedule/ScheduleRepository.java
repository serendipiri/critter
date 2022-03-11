package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByEmployeeListId(long employeeId);

    List<Schedule> findAllByPetListId(long petId);

    @Query("select s from Schedule s inner join s.petList p where p.customer.id = :customerId")
    List<Schedule> getAllByCustomer(@Param("customerId") long customerId);

}
