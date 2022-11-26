package com.kpi.polyreception.JPAEntities;

import javax.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "doctor_id")
    private long id;
    @Column(
            nullable = false
    )
    private String surname;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false
    )
    private String specialty;
    private String cabinet;
}
