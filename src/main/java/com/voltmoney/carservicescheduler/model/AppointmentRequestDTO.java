package com.voltmoney.carservicescheduler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

@Data
public class AppointmentRequestDTO {
    @JsonProperty("operatorId")
    long operatorId;
    @JsonProperty("startHour")
    int startHour;
}
