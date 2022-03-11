package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public interface CustomerDTOMapper {

    @Mapping(source = "petIds", target = "pets", qualifiedByName = "idToPet")
    Customer toCustomer(CustomerDTO customerDTO);

    @Mapping(source = "pets", target = "petIds", qualifiedByName = "petToId")
    CustomerDTO toCustomerDTO(Customer customer);

    @Named("idToPet")
    static Pet idToPet(Long id) {
        Pet pet = new Pet();
        pet.setId(id);
        return pet;
    }

    @Named("petToId")
    static Long petListToId(Pet pet) {
        return pet.getId();
    }

}
