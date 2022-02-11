package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.customer.Customer;
import com.udacity.jdnd.course3.critter.customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.employee.EmployeeRepository;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;


    public Customer saveCustomer(Customer customer) {
        return (Customer) customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return (Employee) employeeRepository.save(employee);
    }

    public Optional getEmployee(long employeeId) {
        return employeeRepository.findById(employeeId);
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


    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            employeeOpt.get().setDaysAvailable(daysAvailable);
            employeeRepository.save(employeeOpt.get());
        }
    }

}
