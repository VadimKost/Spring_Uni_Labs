package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.AppointmentTime;
import com.kpi.polyreception.repository.AppointmentTimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTimeService {

    private final AppointmentTimeRepository appointmentTimeRepository;

    public AppointmentTimeService(AppointmentTimeRepository appointmentTimeRepository) {
        this.appointmentTimeRepository = appointmentTimeRepository;
    }

    public void createAppointmentTime(AppointmentTime appointmentTime) {
        appointmentTimeRepository.getAppointmentTimes().add(appointmentTime);
    }

    public void deleteAppointmentTime(Long id) {
//        List<AppointmentTime> list = appointmentTimeRepository.getAppointmentTimes().stream().filter(item -> item.getId() != appointmentTime).toList();
        AppointmentTime appointmentTime = null;
        for (AppointmentTime at : appointmentTimeRepository.getAppointmentTimes()) {
            if (at.getId().equals(id)) {
                appointmentTime = at;
            }
        }

        if (appointmentTime != null) {
            appointmentTimeRepository.getAppointmentTimes().remove(appointmentTime);
        }
    }

    public void updateAppointmentTime(Long id, AppointmentTime appointmentTimeUpdated) {
        AppointmentTime appointmentTimeToUpdate = appointmentTimeUpdated;

        for(AppointmentTime appointmentTime : appointmentTimeRepository.getAppointmentTimes()) {
            if (appointmentTime.getId().equals(id)) {
                appointmentTimeToUpdate = appointmentTime;
            }
        }

        appointmentTimeToUpdate.setTo(appointmentTimeUpdated.getTo());
        appointmentTimeToUpdate.setFrom(appointmentTimeUpdated.getFrom());
        appointmentTimeToUpdate.setDoctorId(appointmentTimeUpdated.getDoctorId());
        appointmentTimeToUpdate.setDayOfWeek(appointmentTimeUpdated.getDayOfWeek());
    }

    public List<AppointmentTime> getAppointmentTimes() {
        return appointmentTimeRepository.getAppointmentTimes();
    }
    public AppointmentTime findAppointmentTimeById(Long id) {
        AppointmentTime appointmentTime = null;

        for (AppointmentTime at : appointmentTimeRepository.getAppointmentTimes()) {
            if (at.getId().equals(id)) {
                appointmentTime = at;
            }
        }

        return appointmentTime;
    }
}
