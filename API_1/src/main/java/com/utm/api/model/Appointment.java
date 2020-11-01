package com.utm.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Appointment
{
    @Id
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Service service;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm", timezone = "Europe/Chisinau")
    private Date date;
    private boolean available = true;

    public Appointment()
    {
    }
    public Appointment(Long id, User user, Service service, Date date, boolean available)
    {
        this.id = id;
        this.user = user;
        this.service = service;
        this.date = date;
        this.available = available;
    }

    public Long getId()
    {
        return id;
    }
    public Date getDate() { return date; }
    public boolean isAvailable() { return available; }
    public void setId(Long id) { this.id = id; }
    public void setDate(Date date) { this.date = date; }
    public void setAvailable(boolean available) { this.available = available; }

    @NonNull
    public Service getService()
    {
        return service;
    }

    public void setService(@NonNull Service service)
    {
        this.service = service;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
