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

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = convertToCustomer(customerDTO);
        customer = customerService.saveCustomer(customer);
        return convertToCustomerDTO(customer);
    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return customerList.stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getCustomerByPetId(petId);
        return convertToCustomerDTO(customer);
    }


    //TODO: DTO-Entity conversionları  nerde ne şekil yapılsa iyi
    //TODO: DTO vs Projection?

    //TODO: PetList ne olucak id list?? hepsini sorgulayacak mıyım?
    private Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if (!CollectionUtils.isEmpty(customer.getPets())) {
            customerDTO.setPetIds(
                    customer.getPets().stream()
                            .map(Pet::getId)
                            .collect(Collectors.toList()));
        }
        return customerDTO;
    }

}
