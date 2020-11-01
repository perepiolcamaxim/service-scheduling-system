package com.utm.api.model;

import javax.persistence.*;

@Entity
public class Service
{
    @Id
    private Long id;

    private String name;
    private String location;

    public Service()
    {
    }

    public Service(Long id, String name, String location)
    {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Long getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getLocation() { return location; }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
