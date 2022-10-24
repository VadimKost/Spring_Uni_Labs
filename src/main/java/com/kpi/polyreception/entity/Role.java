package com.kpi.polyreception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class Role{
    private Long id;
    private String roleName;
}
