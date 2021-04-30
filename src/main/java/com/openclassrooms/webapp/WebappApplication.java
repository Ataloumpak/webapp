package com.openclassrooms.webapp;

import com.openclassrooms.webapp.repository.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication{
    @Autowired public EmployeeProxy employeeProxy;

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

}
