package com.udacity.jdnd.course3.critter.employee;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDTOMapper {

    Employee toEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO toEmployeeDTO(Employee employee);

}
