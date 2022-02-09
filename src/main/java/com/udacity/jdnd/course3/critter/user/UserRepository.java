package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    @Query("select c from Customer c")
    List<T> findAllCustomers();

}
