package com.utm.api.controller;

import com.utm.api.AppointmentModelAssembler;
import com.utm.api.model.Appointment;
import com.utm.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AppointmentController
{
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentModelAssembler assembler;

    @GetMapping("/appointments")
    public CollectionModel<EntityModel<Appointment>> getAllAppointments()
    {
        List<EntityModel<Appointment>> employees = appointmentService.getAppointments().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(AppointmentController.class).getAllAppointments()).withSelfRel());
    }

    @GetMapping("/appointments/{id}")
    public EntityModel<Appointment> getAppointment(@PathVariable Long id)
    {
        Appointment appointment = appointmentService.getAppointment(id);
        return assembler.toModel(appointment);
    }

    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    Appointment registerAppointment(@RequestBody Appointment appointment)
    {
        return appointmentService.registerAppointment(appointment);
    }

    @PutMapping("/appointments/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<Appointment> updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id)
    {
        Appointment appointment1 = appointmentService.updateAppointment(appointment, id);
        return assembler.toModel(appointment1);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/appointment/{id}")
    void deleteAppointment(@PathVariable Long id)
    {
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/appointments/service/{id}")
    CollectionModel<EntityModel<Appointment>> getAvailableAppointments(@PathVariable Long id)
    {
        List<EntityModel<Appointment>> employees = appointmentService.getAvailableAppointments(id).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(AppointmentController.class)
                .getAvailableAppointments(id))
                .withSelfRel());
    }

    @GetMapping("/appointments/location/{location}")
    CollectionModel<EntityModel<Appointment>> getAvailableAppointmentsByLocation(@PathVariable String location)
    {
        List<EntityModel<Appointment>> employees = appointmentService.
                getAvailableAppointmentsInLocation(location)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(AppointmentController.class)
                .getAvailableAppointmentsByLocation(location))
                .withSelfRel());
    }
}
