package com.utm.api.service;

import com.utm.api.model.Service;
import com.utm.api.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService
{
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> getServices()
    {
        return (List<Service>) serviceRepository.findAll();
    }

    @Override
    public Service saveService(Service service)
    {
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Service service, Long id)
    {
        Service service1 = serviceRepository.findById(id).get();
        service1.setLocation(service.getLocation());
        service1.setName(service.getName());
        return serviceRepository.save(service1);
    }

    @Override
    public List<Service> getServicesByLocation(String location)
    {
        return serviceRepository.findAllByLocation(location);
    }

    @Override
    public void deleteService(Long id)
    {
        serviceRepository.deleteById(id);
    }
}