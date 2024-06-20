package com.voltmoney.carservicescheduler.controller;

import com.voltmoney.carservicescheduler.dto.SlotResponseDTO;
import com.voltmoney.carservicescheduler.model.Appointment;
import com.voltmoney.carservicescheduler.model.AppointmentRequestDTO;
import com.voltmoney.carservicescheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ObjectUtils;

import java.util.List;

@RestController
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/book")
    public ResponseEntity<Object> bookAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return schedulerService.bookAppointment(appointmentRequestDTO);
//        if( appointment != null) return ResponseEntity.ok(appointment);
//        return ResponseEntity.badRequest().build();
    }

//    @PutMapping("/reschedule")
//    public ResponseEntity<Object> rescheduleAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
//        //Appointment appointment = @RequestParam Long appointmentId, @RequestParam int newStartHour
//           return schedulerService.rescheduleAppointment(appointmentRequestDTO);
//        //return ResponseEntity.ok(appointment);
//    }

    @PutMapping("/reschedule/{id}")
    public ResponseEntity<Object> rescheduleAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return schedulerService.rescheduleAppointment(id, appointmentRequestDTO);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Void> cancelAppointment(@RequestParam Long appointmentId) {
        schedulerService.cancelAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByOperator(@RequestParam("id") Long operatorId) {
        List<Appointment> appointments = schedulerService.getAppointmentsByOperator(operatorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/open-slots/")
    public ResponseEntity<List<String>> getOpenSlotsByOperator(@RequestParam("oid") Long operatorId) {
        List<String> openSlots = schedulerService.getOpenSlotsByOperator(operatorId);
        return ResponseEntity.ok(openSlots);
    }

    @GetMapping("/appointment")
    public ResponseEntity<List<Appointment>> getALlAppointments(){
        List<Appointment> appointments=schedulerService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

}
