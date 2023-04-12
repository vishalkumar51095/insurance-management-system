package com.ims.insurancemanagementsystem.insurancepolicy;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/policies")
public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService;

    public InsurancePolicyController(InsurancePolicyService insurancePolicyService) {
        this.insurancePolicyService = insurancePolicyService;
    }


    @GetMapping
    public List<InsurancePolicyModel> getAllPolicies() {
        return insurancePolicyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicyModel> getPolicyById(@PathVariable Long id) {

        return insurancePolicyService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPolicy(@RequestBody InsurancePolicyDto insurancePolicyDto) {
        return insurancePolicyService.createPolicy(insurancePolicyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePolicy(@PathVariable Long id, @RequestBody InsurancePolicyDto insurancePolicyDto) {

        return insurancePolicyService.updatePolicy(id,insurancePolicyDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePolicy(@PathVariable Long id) {

        return insurancePolicyService.deleteById(id);
    }

}
