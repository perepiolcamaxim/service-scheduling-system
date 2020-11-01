package com.utm.api.repository;

import com.utm.api.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long>
{
    List<Appointment> findByAvailableTrueAndService_id(Long id);
    List<Appointment> findByAvailableTrueAndService_location(String location);
}