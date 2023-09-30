package com.example.vendor.Repository;

import com.example.vendor.Entity.VendorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRepository extends MongoRepository<VendorEntity,String> {

}
