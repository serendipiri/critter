package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;


    public Customer saveCustomer(Customer customer) {
        return (Customer) customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(long ownerId) {
        Optional<Customer> customer = customerRepository.findById(ownerId);
        return customer.orElseGet(Customer::new);
    }

    public Customer getCustomerByPetId(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if (pet.isPresent()) {
            return pet.get().getCustomer();
        }
        return null;
    }

}
