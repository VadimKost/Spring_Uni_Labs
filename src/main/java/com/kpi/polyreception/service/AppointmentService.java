package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.Appointment;
import com.kpi.polyreception.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void submitAppointment(Appointment appointment) {
        appointmentRepository.getAppointments().add(appointment);
    }

    public void cancelAppointment(Appointment appointment) {
        appointmentRepository.getAppointments().remove(appointment);
    }

    public List<Appointment> getAppointmentsByPatientId(Long id) {
        List<Appointment> appointments = new ArrayList<>();

        for (Appointment appointment : appointmentRepository.getAppointments()) {
            if (appointment.getPatientId().equals(id)) {
                appointments.add(appointment);
            }
        }

        return appointments;
    }
}
