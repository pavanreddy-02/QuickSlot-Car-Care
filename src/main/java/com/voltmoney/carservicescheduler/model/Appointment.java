package com.voltmoney.carservicescheduler.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operatorId")
    private ServiceOperator serviceOperator;

    private int startHour;
    private int endHour;
}
