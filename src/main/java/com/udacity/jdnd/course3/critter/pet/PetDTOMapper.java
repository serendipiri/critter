package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PetDTOMapper {

    @Mapping(source = "ownerId", target = "customer", qualifiedByName = "idToCustomer")
    Pet toPet(PetDTO petDTO);

    @Mapping(source = "customer", target = "ownerId", qualifiedByName = "customerToId")
    PetDTO toPetDTO(Pet pet);

    @Named("idToCustomer")
    static Customer idToCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }

    @Named("customerToId")
    static Long customerToId(Customer customer) {
        return customer.getId();
    }

}
