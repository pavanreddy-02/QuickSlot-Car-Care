package com.voltmoney.carservicescheduler.service;

import com.voltmoney.carservicescheduler.dto.SlotResponseDTO;
import com.voltmoney.carservicescheduler.model.Appointment;
import com.voltmoney.carservicescheduler.model.AppointmentRequestDTO;
import com.voltmoney.carservicescheduler.model.ServiceOperator;
import com.voltmoney.carservicescheduler.repository.AppointmentRepository;
import com.voltmoney.carservicescheduler.repository.ServiceOperatorRepository;
import com.voltmoney.carservicescheduler.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SchedulerService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ServiceOperatorRepository serviceOperatorRepository;

    @Autowired
    private OperatorService operatorService;

    public ResponseEntity<Object> bookAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Long operatorId=appointmentRequestDTO.getOperatorId();
        int startHour=appointmentRequestDTO.getStartHour();
        if(startHour<0 || startHour>23) return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true,"Invalid start time",null);

        if (isSlotAvailable(operatorId, startHour)) {
            Appointment appointment = new Appointment();

            appointment.setServiceOperator(operatorService.getServiceOperatorByID(operatorId));
            appointment.setStartHour(startHour);
            appointment.setEndHour(startHour + 1);
            return ResponseHandler.generateResponse(HttpStatus.CREATED,false,"Slot Booked",appointmentRepository.save(appointment));
        } else {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true,"Slot already filled for this operator",null);
        }
    }

//    public Appointment rescheduleAppointment(AppointmentRequestDTO appointmentRequestDTO) {
//        //Long appointmentId, int newStartHour
//        Long appointmentId = appointmentRequestDTO.getOperatorId();
//        int newStartHour = appointmentRequestDTO.getStartHour();
//        Appointment appointment = appointmentRepository.findById(appointmentId)
//                .orElseThrow(() -> new RuntimeException("Appointment not found"));
//        Long operatorId = appointment.getServiceOperator().getId();
//        if (isSlotAvailable(operatorId, newStartHour)) {
//            appointment.setStartHour(newStartHour);
//            appointment.setEndHour(newStartHour + 1);
//            return appointmentRepository.save(appointment);
//        } else {
//            throw new RuntimeException("New time slot not available");
//        }
//    }

//    public ResponseEntity<Object> rescheduleAppointment(AppointmentRequestDTO appointmentRequestDTO) {
//        try {
//            Appointment appointment = appointmentRepository.findById(appointmentRequestDTO.getAppointmentId())
//                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
//
//            ServiceOperator serviceOperator = serviceOperatorRepository.findById(appointmentRequestDTO.getOperatorId())
//                    .orElseThrow(() -> new RuntimeException("Service Operator not found"));
//
//            // Update the appointment details
//            appointment.setServiceOperator(serviceOperator);
//            appointment.setStartHour(appointmentRequestDTO.getStartHour());
//            appointment.setEndHour(appointmentRequestDTO.getStartHour() + 1); // Assuming end hour is start hour + 1 for simplicity
//
//            // Save the updated appointment
//            appointmentRepository.save(appointment);
//
//            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Appointment rescheduled successfully", appointment);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, e.getMessage(), null);
//        }
//    }

    public ResponseEntity<Object> rescheduleAppointment(Long appointmentId, AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        ServiceOperator serviceOperator = serviceOperatorRepository.findById(appointmentRequestDTO.getOperatorId())
                .orElseThrow(() -> new RuntimeException("Service Operator not found"));

        int newStartHour = appointmentRequestDTO.getStartHour();
        if (newStartHour < 0 || newStartHour > 23) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Invalid start time", null);
        }

        if (isSlotAvailable(serviceOperator.getId(), newStartHour)) {
            appointment.setServiceOperator(serviceOperator);
            appointment.setStartHour(newStartHour);
            appointment.setEndHour(newStartHour + 1);
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Appointment rescheduled successfully", appointmentRepository.save(appointment));
        } else {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "New time slot not available", null);
        }
    }

    public void cancelAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    public List<Appointment> getAppointmentsByOperator(Long operatorId) {
        ServiceOperator so = serviceOperatorRepository.findById(operatorId).orElseThrow();
        List<Appointment> appointments = appointmentRepository.findByServiceOperator(so);
        //appointmentRepository.findByOperatorId(operatorId)
        return appointments;
    }

    public List<String> getOpenSlotsByOperator(Long operatorId) {
        ServiceOperator so = serviceOperatorRepository.findById(operatorId).orElseThrow();
        List<Appointment> appointments = appointmentRepository.findByServiceOperator(so);
        boolean[] occupied = new boolean[24];
        for (Appointment appointment : appointments) {
            occupied[appointment.getStartHour()] = true;
        }

        List<String> openSlots = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < 24; i++) {
            if (!occupied[i] && start == -1) {
                start = i;
            } else if (occupied[i] && start != -1) {
                openSlots.add(start + "-" + i);
                start = -1;
            }
        }
        if (start != -1) {
            openSlots.add(start + "-24");
        }
        return openSlots;
    }

    private boolean isSlotAvailable(Long operatorId, int startHour) {
        // TODO:
        List<Long> appointments = appointmentRepository.findByOperatorIdAndStartHourBetween(operatorId,startHour);
        log.info(appointments.toString());
        return appointments.isEmpty();
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
}
