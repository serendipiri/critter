package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //TODO : transactional nerde kullanmak mantıklı?

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return (Customer) userRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return userRepository.findAllCustomers();
    }

    public Employee saveEmployee(Employee employee) {
        return (Employee) userRepository.save(employee);
    }

    public Optional getEmployee(long employeeId) {
        return userRepository.findById(employeeId);
    }
}
