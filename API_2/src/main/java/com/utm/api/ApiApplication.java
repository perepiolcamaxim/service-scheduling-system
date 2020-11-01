package com.utm.api;

import com.utm.api.model.Appointment;
import com.utm.api.model.Service;
import com.utm.api.repository.AppointmentRepository;
import com.utm.api.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableCaching
public class ApiApplication
{
	private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AppointmentRepository repository, ServiceRepository serviceRepository)
	{
		return (args) -> {

			Service service = new Service(1L, "Examen practic", "Chisinau");
			serviceRepository.save(service);

			Appointment appointment = new Appointment();
			appointment.setId(1L);
			appointment.setDate(new Date());
			appointment.setId(2L);
			appointment.setService(service);
			repository.save(appointment);
			appointment.setId(3L);
			repository.save(appointment);
			appointment.setId(4L);
			repository.save(appointment);
		};
	}
}
