package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository {
    private final AppointmentTimeRepository appointmentTimeRepository;
    private Schedule schedule;

    public ScheduleRepository(AppointmentTimeRepository appointmentRepository) {
        this.appointmentTimeRepository = appointmentRepository;
        schedule = new Schedule(1L, appointmentRepository.getAppointmentTimes());
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
