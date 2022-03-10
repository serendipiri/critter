package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.employee.Employee;
import com.udacity.jdnd.course3.critter.employee.EmployeeDTO;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DtoMapper {

//    @InheritInverseConfiguration(name = "scheduleToScheduleDTO")
    @Mapping(source = "employeeIds", target = "employeeList", qualifiedByName = "idToEmployee")
    @Mapping(source = "petIds", target = "petList", qualifiedByName = "idToPet")
    Schedule scheduleDTOToSchedule(ScheduleDTO scheduleDTO);

    @Mapping(source = "employeeList", target = "employeeIds", qualifiedByName = "employeeToId")
    @Mapping(source = "petList", target = "petIds", qualifiedByName = "petToId")
    ScheduleDTO scheduleToScheduleDTO(Schedule schedule);

    @Named("employeeToId")
    static Long employeeListToId(Employee employee) {
        return employee.getId();
    }

    @Named("petToId")
    static Long petListToId(Pet pet) {
        return pet.getId();
    }

    @Named("idToEmployee")
    static Employee idToEmployee(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }

    @Named("idToPet")
    static Pet idToPet(Long id) {
        Pet pet = new Pet();
        pet.setId(id);
        return pet;
    }

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Pet petDTOToPet(PetDTO petDTO);

    PetDTO petToPetDTO(PetDTO petDTO);

}
