package com.utm.api.service;

import com.utm.api.model.Appointment;
import java.util.List;

public interface AppointmentService
{
    List<Appointment> getAppointments();
    Appointment registerAppointment(Appointment appointment);
    void deleteAppointment(Long id);
    List<Appointment> getAvailableAppointments(Long id);
    List<Appointment> getAvailableAppointmentsInLocation(String location);
    Appointment updateAppointment(Appointment appointment, Long id);
    Appointment getAppointment(Long id);
}
