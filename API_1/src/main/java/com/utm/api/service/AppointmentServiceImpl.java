package com.utm.api.service;

import com.utm.api.model.Appointment;
import com.utm.api.model.Service;
import com.utm.api.model.User;
import com.utm.api.repository.AppointmentRepository;
import com.utm.api.repository.ServiceRepository;
import com.utm.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class AppointmentServiceImpl implements AppointmentService
{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Appointment> getAppointments()
    {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointment(Long id)
    {
        return appointmentRepository.findById(id).get();
    }

    @Override
    public Appointment registerAppointment(Appointment appointment)
    {
        appointment.setAvailable(false);
        Service service = appointment.getService();
        User user = appointment.getUser();

        if(!serviceRepository.existsById(service.getId()))
            serviceRepository.save(service);

        if(!userRepository.existsById(user.getId()))
            userRepository.save(user);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, Long id)
    {
        Appointment appointment1 = appointmentRepository.findById(id).get();
        Service service = appointment.getService();
        User user = appointment.getUser();
        if(!serviceRepository.existsById(service.getId()))
            serviceRepository.save(service);
        if(!userRepository.existsById(user.getId()))
            userRepository.save(user);
        appointment1.setUser(user);
        appointment1.setService(service);
        appointment1.setDate(appointment.getDate());
        appointment1.setAvailable(false);
        return appointmentRepository.save(appointment1);
    }

    @Override
    public void deleteAppointment(Long id)
    {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getAvailableAppointments(Long id)
    {
        return appointmentRepository.findByAvailableTrueAndService_id(id);
    }

    @Override
    public List<Appointment> getAvailableAppointmentsInLocation(String location)
    {
        return appointmentRepository.findByAvailableTrueAndService_location(location);
    }
}
