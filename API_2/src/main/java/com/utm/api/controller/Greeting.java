package com.utm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting
{
    @Autowired
    private Environment environment;

    @GetMapping("/greeting")
    String greeting()
    {
        return "Hello from App running on port " + environment.getProperty("local.server.port") + "!";
    }
}
