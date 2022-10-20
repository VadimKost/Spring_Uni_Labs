package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Appointment {

    private Long id;
    private Long patientId;
    private Long appointmentTimeId;

}
