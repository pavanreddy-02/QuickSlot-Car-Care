package com.voltmoney.carservicescheduler.repository;

import com.voltmoney.carservicescheduler.dto.SlotResponseDTO;
import com.voltmoney.carservicescheduler.model.Appointment;
import com.voltmoney.carservicescheduler.model.ServiceOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByServiceOperator(ServiceOperator so);

    @Query("SELECT a.id FROM Appointment a JOIN a.serviceOperator ap WHERE ap.id = :id and a.startHour = :startHour ")
    List<Long> findByOperatorIdAndStartHourBetween(@Param("id")Long operatorId,@Param("startHour") int startHour);
}