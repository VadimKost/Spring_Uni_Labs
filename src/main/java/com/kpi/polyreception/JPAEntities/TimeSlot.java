package com.kpi.polyreception.JPAEntities;

import javax.persistence.*;

@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "timeslot_id")
    private long id;
    @Column(
            nullable = false
    )
    private String dayOfWeek;
    @Column(
            nullable = false
    )
    private String startTime;
    @Column(
            nullable = false
    )
    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctors;
}
