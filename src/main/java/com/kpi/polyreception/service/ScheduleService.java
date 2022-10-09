package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.Doctor;
import com.kpi.polyreception.entity.Schedule;
import com.kpi.polyreception.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule getSchedule(Doctor doctor) {
        Schedule scheduleFound = null;

        for(Schedule schedule : scheduleRepository.getSchedules()) {
            if (schedule.getDoctorId().equals(doctor.getId())) {
                scheduleFound = schedule;
            }
        }

        return scheduleFound;
    }
}
