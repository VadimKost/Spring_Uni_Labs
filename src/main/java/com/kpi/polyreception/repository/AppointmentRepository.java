package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.Appointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppointmentRepository {
    private List<Appointment> appointments = new ArrayList<>() {{
        add(new Appointment(1L, 1L, 1L));
        add(new Appointment(2L, 1L, 3L));
        add(new Appointment(3L, 2L, 2L));
        add(new Appointment(4L, 2L, 10L));
        add(new Appointment(5L, 2L, 7L));
        add(new Appointment(6L, 3L, 6L));
        add(new Appointment(7L, 4L, 4L));
    }};

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
