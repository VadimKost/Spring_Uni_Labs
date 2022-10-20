package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {
    private Schedule schedule = new Schedule(1L, new AppointmentTimeRepository().getAppointmentTimes());

    public Schedule getSchedule() {
        return schedule;
    }
}
