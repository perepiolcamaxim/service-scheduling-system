package com.utm.api.controller;

import com.utm.api.model.Service;
import com.utm.api.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ServiceController
{
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public List<Service> getServices()
    {
        return serviceService.getServices();
    }

    @PostMapping("/service")
    @ResponseStatus(HttpStatus.CREATED)
    Service saveService(@RequestBody Service service)
    {
        return serviceService.saveService(service);
    }

    @PutMapping("/service/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    Service updateService(@RequestBody Service service, @PathVariable Long id)
    {
        return serviceService.updateService(service, id);
    }

    @GetMapping("/service/{location}")
    List<Service> getServicesByLocation(@PathVariable String location)
    {return  serviceService.getServicesByLocation(location);}

    @DeleteMapping("/service/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteService(@PathVariable Long id)
    {
        serviceService.deleteService(id);
    }
}
