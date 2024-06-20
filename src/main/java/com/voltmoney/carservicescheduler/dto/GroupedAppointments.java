package com.voltmoney.carservicescheduler.dto;

import com.voltmoney.carservicescheduler.model.ServiceOperator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupedAppointments {
    private ServiceOperator serviceOperator;
    private List<SlotResponseDTO> bookedSlots;

    public GroupedAppointments(ServiceOperator serviceOperator) {
        this.serviceOperator = serviceOperator;
        this.bookedSlots = new ArrayList<>();
    }
}
