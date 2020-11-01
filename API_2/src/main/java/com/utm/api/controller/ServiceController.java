package com.utm.api.controller;

import com.utm.api.model.Service;
import com.utm.api.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "Service")
public class ServiceController {
    private final Logger logger = LoggerFactory.getLogger(ServiceController.class);
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    @Cacheable()
    public List<Service> getServices() {
        logger.info(("Service Controller : /services/  call"));
        return serviceService.getServices();
    }

    @GetMapping("/services/{location}")
    @Cacheable()
    List<Service> getServicesByLocation(@PathVariable String location) {
        logger.info("Service Controller : /services/location  call");
        return serviceService.getServicesByLocation(location);
    }

    @PostMapping("/services")
    @ResponseStatus(HttpStatus.CREATED)
    Service saveService(@RequestBody Service service) {
        return serviceService.saveService(service);
    }

    @PutMapping("/services/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    Service updateService(@RequestBody Service service, @PathVariable Long id) {
        logger.info("Service Controller : /services/id  call");
        return serviceService.updateService(service, id);
    }

    @DeleteMapping("/services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
}
