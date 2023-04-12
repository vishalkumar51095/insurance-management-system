package com.ims.insurancemanagementsystem.claim;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public List<ClaimModel> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("{id}")
    public ClaimModel getClaimById(@PathVariable Long id) throws ResourceNotFoundException {
        return claimService.getClaimById(id);
    }

    @PostMapping
    public ResponseEntity<?> createClaim(@RequestBody ClaimDto claimDto) throws ResourceNotFoundException {
        return claimService.createClaim(claimDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateClaim(@PathVariable Long id, @RequestBody ClaimDto claimDto) throws ResourceNotFoundException {
        return claimService.updateClaim(id, claimDto);
    }

    @DeleteMapping("{id}")
    public void deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
    }
}

