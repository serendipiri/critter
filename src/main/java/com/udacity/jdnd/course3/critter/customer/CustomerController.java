package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDTOMapper customerDTOMapper;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerDTOMapper.toCustomer(customerDTO);
        customer = customerService.saveCustomer(customer);
        return customerDTOMapper.toCustomerDTO(customer);
    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return customerList.stream()
                .map(customerDTOMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getCustomerByPetId(petId);
        return customerDTOMapper.toCustomerDTO(customer);
    }

}
