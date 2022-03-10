package com.udacity.jdnd.course3.critter.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesOfEmployee(long employeeId) {
        return scheduleRepository.findAllByEmployeeListId(employeeId);
    }

    public List<Schedule> getSchedulesOfPet(long petId) {
        return scheduleRepository.findAllByPetListId(petId);
    }

    public List<Schedule> getSchedulesOfCustomer(long customerId) {
        return scheduleRepository.getAllByCustomer(customerId);
    }
}
