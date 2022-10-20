package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Schedule {

    private Long id;
    private List<AppointmentTime> appointmentTimes;

}
