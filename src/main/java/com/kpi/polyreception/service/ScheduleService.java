package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.AppointmentTime;
import com.kpi.polyreception.entity.Doctor;
import com.kpi.polyreception.entity.Schedule;
import com.kpi.polyreception.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule getScheduleByDoctor(Doctor doctor) {
        List<AppointmentTime> appointmentTimes = scheduleRepository.getSchedule().getAppointmentTimes();
        Schedule scheduleFound = new Schedule(new Random().nextLong(), new ArrayList<>());

        for (AppointmentTime appointmentTime : appointmentTimes) {
            if (appointmentTime.getDoctorId().equals(doctor.getId())) {
                scheduleFound.getAppointmentTimes().add(appointmentTime);
            }
        }

        return scheduleFound;
    }
}
