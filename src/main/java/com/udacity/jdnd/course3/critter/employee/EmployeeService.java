package com.udacity.jdnd.course3.critter.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return (Employee) employeeRepository.save(employee);
    }

    public Optional getEmployee(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            employeeOpt.get().setDaysAvailable(daysAvailable);
        }
    }

    public List<Employee> getAvailableEmployeeBySkills(LocalDate date, Set<EmployeeSkill> skills) {
        DayOfWeek availableDay = DayOfWeek.from(date);
        return employeeRepository.getAvailableEmployeeBySkillList(availableDay, skills, skills.size());
    }

}
