package com.voltmoney.carservicescheduler.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlotResponseDTO {
    @Id
    long id;
    @Id
    int startHour;
    @Id
    int endHour;
}
