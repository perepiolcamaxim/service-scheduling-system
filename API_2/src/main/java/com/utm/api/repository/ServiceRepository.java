package com.utm.api.repository;

import com.utm.api.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>
{
    List<Service> findAllByLocation(String location);
}