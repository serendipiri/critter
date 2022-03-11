package com.udacity.jdnd.course3.critter.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleDTOMapper mapper;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = mapper.toSchedule(scheduleDTO);
        schedule = scheduleService.createSchedule(schedule);
        return mapper.toScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> list = scheduleService.getAllSchedules();
        return getScheduleDtoList(list);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> list = scheduleService.getSchedulesOfPet(petId);
        return getScheduleDtoList(list);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> list = scheduleService.getSchedulesOfEmployee(employeeId);
        return getScheduleDtoList(list);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> list = scheduleService.getSchedulesOfCustomer(customerId);
        return getScheduleDtoList(list);
    }


    private List<ScheduleDTO> getScheduleDtoList(List<Schedule> list) {
        return list.stream()
                .map(mapper::toScheduleDTO)
                .collect(Collectors.toList());
    }

}
