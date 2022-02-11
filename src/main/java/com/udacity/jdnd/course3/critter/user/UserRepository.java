package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    @Query("select c from Customer c")
    List<T> findAllCustomers();

    @Query("select c from Customer c where :petId in (pets.id)")
    Optional<Customer> findCustomerByPetId(long petId);
}
