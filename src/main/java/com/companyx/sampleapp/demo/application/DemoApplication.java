package com.companyx.sampleapp.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.companyx.sampleapp.demo.dao.CustomerDao;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		CustomerDao custDao = new CustomerDao();
		
		System.out.print("Customer: "+ custDao.findByID(1));
	}
}
