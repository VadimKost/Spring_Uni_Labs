package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private Long id;
    private Long appointmentTimeId;
    private Long doctorId;

}
