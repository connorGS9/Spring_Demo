package com.connorDev.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args ->  {
            Student Mariam = new Student("Mariam", "mariam@gmail.com",
                    LocalDate.of(2000, Month.MARCH, 12)
            );

            Student Alex = new Student("Alex", "alex@gmail.com",
                    LocalDate.of(2004, Month.MARCH, 12)
            );

            Student testAlex = new Student("Alex", "alex@gmail.com",
                    LocalDate.of(2004, Month.MARCH, 12)
            );

            repository.saveAll(List.of(Mariam,Alex,testAlex));
            };
        }

    }
