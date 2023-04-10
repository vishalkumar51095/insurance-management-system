package com.ims.insurancemanagementsystem.claim;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/api/claims")
    public List<ClaimModel> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("/api/claims/{id}")
    public ClaimModel getClaimById(@PathVariable Long id) throws ResourceNotFoundException {
        return claimService.getClaimById(id);
    }

    @PostMapping("/api/claims")
    public ResponseEntity<?> createClaim(@RequestBody ClaimDto claimDto) throws ResourceNotFoundException {
        return claimService.createClaim(claimDto);
    }

    @PutMapping("/api/claims/{id}")
    public ResponseEntity<?> updateClaim(@PathVariable Long id, @RequestBody ClaimDto claimDto) throws ResourceNotFoundException {
        return claimService.updateClaim(id, claimDto);
    }

    @DeleteMapping("/api/claims/{id}")
    public void deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
    }
}

