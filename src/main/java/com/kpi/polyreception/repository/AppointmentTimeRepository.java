package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.AppointmentTime;
import com.kpi.polyreception.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class AppointmentTimeRepository {
    private List<AppointmentTime> appointmentTimes = new ArrayList<>() {
        {
            add(new AppointmentTime(1L, 1L, "8:00", "9:00", "Monday"));
            add(new AppointmentTime(2L, 1L, "10:00", "11:00", "Wednesday"));
            add(new AppointmentTime(3L, 2L, "8:30", "9:00", "Tuesday"));
            add(new AppointmentTime(4L, 2L, "12:00", "13:30", "Thursday"));
            add(new AppointmentTime(5L, 3L, "13:00", "13:30", "Monday"));
            add(new AppointmentTime(6L, 3L, "8:30", "9:00", "Friday"));
            add(new AppointmentTime(7L, 4L, "10:00", "10:30", "Wednesday"));
            add(new AppointmentTime(8L, 4L, "11:30", "12:00", "Thursday"));
            add(new AppointmentTime(9L, 5L, "8:30", "9:00", "Friday"));
            add(new AppointmentTime(10L, 5L, "12:00", "13:00", "Tuesday"));
        }
    };

    public List<AppointmentTime> getAppointmentTimes() {
        return appointmentTimes;
    }

}
