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
//    @Query("SELECT ap FROM Appointment a JOIN a.serviceOperator ap WHERE a.id = :id")
    // NEW com.voltmoney.carservicescheduler.dto.SlotResponseDTO(a.id,a.startHour,a.endHour)
    @Query(value = "SELECT NEW com.voltmoney.carservicescheduler.dto.SlotResponseDTO(a.id,a.startHour,a.endHour) FROM Appointment a JOIN a.serviceOperator ap WHERE a.id = :id")
    List<Object> findByOperatorId(@Param("id") Long operatorId);

    List<Appointment> findByServiceOperator(ServiceOperator so);
//    List<Appointment> findByStartHour(int startHour);
    @Query("SELECT a.id FROM Appointment a JOIN a.serviceOperator ap WHERE ap.id = :id and a.startHour = :startHour ")
    List<Long> findByOperatorIdAndStartHourBetween(@Param("id")Long operatorId,@Param("startHour") int startHour);
}