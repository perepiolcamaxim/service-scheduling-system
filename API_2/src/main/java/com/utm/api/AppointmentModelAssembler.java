package com.utm.api;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.utm.api.controller.AppointmentController;
import com.utm.api.model.Appointment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AppointmentModelAssembler implements RepresentationModelAssembler<Appointment, EntityModel<Appointment>> {

    @Override
    public EntityModel<Appointment> toModel(Appointment employee) {

        return EntityModel.of(employee, //
                linkTo(methodOn(AppointmentController.class).getAppointment(employee.getId())).withSelfRel(),
                linkTo(methodOn(AppointmentController.class).getAllAppointments()).withRel("appointments"));
    }
}
