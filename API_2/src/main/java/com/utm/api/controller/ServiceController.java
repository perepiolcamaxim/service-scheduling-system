package com.utm.api.controller;

import com.utm.api.model.Service;
import com.utm.api.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
public class ServiceController
{
    private final Logger logger = LoggerFactory.getLogger(ServiceController.class);
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    @Cacheable(value = "services")
    public List<Service> getServices()
    {
        logger.info(("Service Controller : /services/  call"));
        return serviceService.getServices();
    }

    @PostMapping("/services")
    @ResponseStatus(HttpStatus.CREATED)
    Service saveService(@RequestBody Service service)
    {
        return serviceService.saveService(service);
    }

    @PutMapping("/services/{id}")
    @Cacheable(value = "services")
    @ResponseStatus(HttpStatus.CREATED)
    Service updateService(@RequestBody Service service, @PathVariable Long id)
    {
        logger.debug("Service Controller : /services/id  call");
        return serviceService.updateService(service, id);
    }

    @GetMapping("/services/{location}")
    List<Service> getServicesByLocation(@PathVariable String location)
    {logger.debug("Service Controller : /services/location  call");
        return  serviceService.getServicesByLocation(location);}

    @DeleteMapping("/services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteService(@PathVariable Long id)
    {
        serviceService.deleteService(id);
    }
}
