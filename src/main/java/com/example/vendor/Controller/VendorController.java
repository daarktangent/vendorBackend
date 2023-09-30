package com.example.vendor.Controller;
import com.example.vendor.Entity.VendorEntity;
import com.example.vendor.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @GetMapping("")
    public List<VendorEntity> getVendors() {
        System.out.println(vendorRepository.findAll());
        return vendorRepository.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<String> createVendor(@RequestBody VendorEntity vendor) {
        try {
            vendorRepository.save(vendor);
            return ResponseEntity.ok("Vendor created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating vendor: " + e.getMessage());
        }
    }

}


