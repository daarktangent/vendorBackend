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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable String id) {
        try {
            Optional<VendorEntity> vendor = vendorRepository.findById(id);
            if (vendor.isPresent()) {
                vendorRepository.deleteById(id);
                return ResponseEntity.ok("Vendor deleted successfully.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting vendor: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVendor(@PathVariable String id, @RequestBody VendorEntity updatedVendor) {
        try {
            Optional<VendorEntity> existingVendor = vendorRepository.findById(id);
            if (existingVendor.isPresent()) {
                updatedVendor.setId(id); // Set the ID to match the existing vendor
                vendorRepository.save(updatedVendor);
                return ResponseEntity.ok("Vendor updated successfully.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating vendor: " + e.getMessage());
        }
    }
}


