package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.AppointmentTime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class AppointmentTimeRepository {
    private List<AppointmentTime> appointmentTimes = new ArrayList<>();

    public List<AppointmentTime> getAppointmentTimes() {
        return appointmentTimes;
    }

}
