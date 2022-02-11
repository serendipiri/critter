package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"pets"}, callSuper = true)
public class Customer extends User {

    private String phoneNumber;

    @Column(length = 1024)
    private String notes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<Pet> pets = new HashSet<>();

}
