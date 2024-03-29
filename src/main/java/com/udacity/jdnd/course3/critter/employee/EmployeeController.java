package com.udacity.jdnd.course3.critter.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDtoMapper;

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.saveEmployee(employeeDtoMapper.toEmployee(employeeDTO));
        return employeeDtoMapper.toEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        //TODO: hataları nasıl ele almalı?
        Employee employee = (Employee) employeeService.getEmployee(employeeId)
                .orElseGet(Employee::new);
        return employeeDtoMapper.toEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> list = employeeService.getAvailableEmployeeBySkills(employeeDTO.getDate(), employeeDTO.getSkills());
        return list.stream()
                .map(employeeDtoMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    //TODO : genericlerle yapılsa, dto ya da inheritance yapayım dicem ama
    // dto - entity çiftinin aynı tip olduğunu nasıl garantilerim?
//    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
//        Employee employee = new Employee();
//        BeanUtils.copyProperties(employeeDTO, employee);
//        return employee;
//    }
//
//    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        BeanUtils.copyProperties(employee, employeeDTO);
//        return employeeDTO;
//    }

}
