package com.voltmoney.carservicescheduler.service;

import com.voltmoney.carservicescheduler.model.ServiceOperator;
import com.voltmoney.carservicescheduler.repository.ServiceOperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {

    @Autowired
    private ServiceOperatorRepository serviceOperatorRepository;

    public List<ServiceOperator> getAllServiceOperators() {
        return serviceOperatorRepository.findAll();
    }

//    public ServiceOperator getServiceOperatorById(Long id) {
//        return serviceOperatorRepository.findById(id).orElseThrow(() -> new RuntimeException("ServiceOperator not found"));
//    }

    public ServiceOperator getServiceOperatorByID(Long id){
        ServiceOperator so = serviceOperatorRepository.findById(id).orElseThrow();
        return so;
    }

    public ServiceOperator createServiceOperator(ServiceOperator serviceOperator) {
        return serviceOperatorRepository.save(serviceOperator);
    }

    public ServiceOperator updateServiceOperator(Long id, ServiceOperator updatedServiceOperator) {
        ServiceOperator existingServiceOperator = serviceOperatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceOperator not found"));
        existingServiceOperator.setName(updatedServiceOperator.getName());
        return serviceOperatorRepository.save(existingServiceOperator);
    }

    public void deleteServiceOperator(Long id) {
        serviceOperatorRepository.deleteById(id);
    }
}


