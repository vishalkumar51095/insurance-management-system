package com.ims.insurancemanagementsystem.insurancepolicy;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InsurancePolicyService {
    List<InsurancePolicyModel> findAll();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> createPolicy(InsurancePolicyDto insurancePolicyDto);
    
    ResponseEntity<?> updatePolicy(Long id, InsurancePolicyDto insurancePolicyDto);

    ResponseEntity<?> deleteById(Long id);
}
