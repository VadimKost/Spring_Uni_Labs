package com.kpi.polyreception.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentTime {
    @ApiModelProperty(notes = "Product ID", example = "1", required = true)
    private Long id;
    private Long doctorId;
    private String from;
    private String to;
    private String dayOfWeek;

}
