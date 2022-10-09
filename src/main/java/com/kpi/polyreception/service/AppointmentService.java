package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.Appointment;
import com.kpi.polyreception.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void submitAppointment(Appointment appointment) {
        appointmentRepository.getAppointments().put(appointment.getId(), appointment);
    }

    public void cancelAppointment(Long id, Appointment appointment) {
        appointmentRepository.getAppointments().remove(id, appointment);
    }
}
