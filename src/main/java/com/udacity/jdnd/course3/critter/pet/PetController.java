package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.customer.Customer;
import com.udacity.jdnd.course3.critter.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;
    private UserService userService;

    public PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer = null;
        Pet pet = convertDtoToEntity(petDTO);

        if (petDTO.getOwnerId() > 0) {
            customer = userService.getCustomer(petDTO.getOwnerId());
            if (customer.getId() == null) {
                //TODO: hata vermeli sanki? -customer yoksa pet kimin olucu? veya bilemedim neyse. bunu nerde ele almak mantıklı
            }
            pet.setCustomer(customer);
        }

        pet = petService.savePet(pet);

        if (customer != null) {
            customer.getPets().add(pet);
        }

        return convertEntityToDto(pet);
    }


    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return convertEntityToDto(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);

        return pets.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    private PetDTO convertEntityToDto(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());// TODO: bunu nerde yapmak mantıklı?
        return petDTO;
    }

    private Pet convertDtoToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
