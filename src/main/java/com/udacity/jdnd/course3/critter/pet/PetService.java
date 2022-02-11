package com.udacity.jdnd.course3.critter.pet;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPet(long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        return optionalPet.orElseGet(Pet::new);
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
