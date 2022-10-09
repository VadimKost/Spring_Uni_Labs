package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.Appointment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AppointmentRepository {
    private Map<Long, Appointment> appointments = new HashMap<>();

    public Map<Long, Appointment> getAppointments() {
        return appointments;
    }
}
