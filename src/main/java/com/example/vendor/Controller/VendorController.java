package com.example.vendor.Controller;
import com.example.vendor.Entity.VendorEntity;
import com.example.vendor.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @GetMapping("")//this lists all vendors
    public List<VendorEntity> getVendors() {

        return vendorRepository.findAll();
    }
    @GetMapping("{id}")//this lists single vendor of id
    public Optional<VendorEntity> getVendors(@PathVariable String id) {
        return vendorRepository.findById(id);
    }
    //for pagination
    @GetMapping("/page")
    public ResponseEntity<Page<VendorEntity>> getVendors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VendorEntity> vendorPage = vendorRepository.findAll(pageable);
        return ResponseEntity.ok(vendorPage);
    }
    @PostMapping("/add")
    public VendorEntity createVendor(@RequestBody VendorEntity vendor) {
            vendorRepository.save(vendor);
        System.out.println("add complete");
            return vendor;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable String id) {
        try {
            Optional<VendorEntity> vendor = vendorRepository.findById(id);
            if (vendor.isPresent()) {
                vendorRepository.deleteById(id);
                System.out.println("Vendor deleted successfully.");
                return ResponseEntity.noContent().build();
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
                System.out.println("update complete");
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating vendor: " + e.getMessage());
        }
    }
}


