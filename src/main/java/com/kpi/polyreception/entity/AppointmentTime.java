package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AppointmentTime {
    private Long id;
    private Long doctorId;
    private Date from;
    private Date to;

}
