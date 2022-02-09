package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Customer extends User {

    private String phoneNumber;

    @Column(length = 1024)
    private String notes;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

}
