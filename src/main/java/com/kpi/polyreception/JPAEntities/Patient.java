package com.kpi.polyreception.JPAEntities;

import javax.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "patient_id")
    private long id;
    @Column(
            nullable = false
    )
    private String surname;
    @Column(
            nullable = false
    )
    private String name;
}
