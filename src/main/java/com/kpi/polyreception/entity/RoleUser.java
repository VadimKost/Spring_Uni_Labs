package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleUser {

    private Long id;
    private Long roleId;
    private Long userId;
}
