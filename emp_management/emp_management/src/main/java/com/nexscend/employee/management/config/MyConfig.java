//package com.nexscend.employee.management.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class MyConfig {
//
//	 @Bean
//	    DataSource dataSource(){
//	        DriverManagerDataSource source = new DriverManagerDataSource();
//	        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
//	        source.setUrl("jdbc:mysql://localhost:3306/emp_management");
//	        source.setUsername("root");
//	        source.setPassword("root");
//	        return source;
//	    }
//}