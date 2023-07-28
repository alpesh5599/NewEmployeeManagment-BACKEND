package com.nexscend.employee.management;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nexscend.employee.management.utils.Constants;

@SpringBootApplication(scanBasePackages = "com.nexscend.employee.management")
@EntityScan("com.nexscend.employee.management.entity")
@EnableJpaRepositories("com.nexscend.employee.management.repository")
public class EmpManagementApplication {
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(Constants.LOG4j_FILE_PATH);
		SpringApplication.run(EmpManagementApplication.class, args);
	}
	
}