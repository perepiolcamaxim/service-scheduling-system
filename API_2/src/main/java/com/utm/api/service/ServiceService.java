package com.utm.api.service;

import com.utm.api.model.Service;
import java.util.List;
import java.util.Optional;

public interface ServiceService
{
    List<Service> getServices();
    Service saveService(Service service);
    List<Service> getServicesByLocation(String location);
    Service updateService(Service service, Long id);
    void deleteService(Long id);
}
