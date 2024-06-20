package com.voltmoney.carservicescheduler.controller;

import com.voltmoney.carservicescheduler.model.ServiceOperator;
import com.voltmoney.carservicescheduler.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceoperators")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @GetMapping
    public ResponseEntity<List<ServiceOperator>> getAllServiceOperators() {
        List<ServiceOperator> serviceOperators = operatorService.getAllServiceOperators();
        return ResponseEntity.ok(serviceOperators);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOperator> getServiceOperatorById(@PathVariable Long id) {
        ServiceOperator serviceOperator = operatorService.getServiceOperatorByID(id);
        return ResponseEntity.ok(serviceOperator);
    }

    @PostMapping
    public ResponseEntity<ServiceOperator> createServiceOperator(@RequestBody ServiceOperator serviceOperator) {
        ServiceOperator createdServiceOperator = operatorService.createServiceOperator(serviceOperator);
        return ResponseEntity.ok(createdServiceOperator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOperator> updateServiceOperator(@PathVariable Long id, @RequestBody ServiceOperator updatedServiceOperator) {
        ServiceOperator serviceOperator = operatorService.updateServiceOperator(id, updatedServiceOperator);
        return ResponseEntity.ok(serviceOperator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceOperator(@PathVariable Long id) {
        operatorService.deleteServiceOperator(id);
        return ResponseEntity.ok().build();
    }
}

