package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.customer.Customer;
import com.udacity.jdnd.course3.critter.customer.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;

    private final CustomerService customerService;

    public PetService(PetRepository petRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    //TODO:???????
    @Transactional
    public Pet savePet(Pet pet) {

        Customer customer = null;

        pet = petRepository.save(pet);

        if (pet.getCustomer() != null) {
            customer = customerService.getCustomer(pet.getCustomer().getId());
        }
        if (customer == null) {
            //TODO: hata
        }
        customer.getPets().add(pet);
        customerService.saveCustomer(customer);

        return pet;
    }

    public Pet getPet(long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        return optionalPet.orElseGet(Pet::new);
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findAllByCustomerId(ownerId);
    }

}
