package com.utm.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public PreFilterService simpleFilterService() {
		return new PreFilterService();
	}

	@Bean
	public PostFilterService simpleFilter2Service() {
		return new PostFilterService();
	}

	@Bean
	public PreFilterAppointment simpleFilterAppointment() {
		return new PreFilterAppointment();
	}

	@Bean
	public PostFilterAppointment simpleFilter2Appointment() {
		return new PostFilterAppointment();
	}
}
