package com.voltmoney.carservicescheduler.repository;

import com.voltmoney.carservicescheduler.model.ServiceOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOperatorRepository extends JpaRepository<ServiceOperator, Long> {
}
