package com.example.vendor;

import com.example.vendor.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class VendorApplication {
	@Autowired
	VendorRepository vendorRepository;
	public static void main(String[] args) {

		SpringApplication.run(VendorApplication.class, args);
	}

}
