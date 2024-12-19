package com.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.taskmanager")
@EntityScan(basePackages = "com.taskmanager.Entity")
@EnableJpaRepositories(basePackages = "com.taskmanager.Repository")
public class TaskManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}
}
