package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentTime {

    private Long id;
    private Long doctorId;
    private String from;
    private String to;
    private String dayOfWeek;

}
