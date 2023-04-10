package com.ims.insurancemanagementsystem.claim;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClaimService {
    ClaimModel getClaimById(Long id) throws ResourceNotFoundException;

    ResponseEntity<?> createClaim(ClaimDto claimDto) throws ResourceNotFoundException;

    ResponseEntity<?> updateClaim(Long id, ClaimDto claimDto) throws ResourceNotFoundException;

    ResponseEntity<?> deleteClaim(Long id);

    List<ClaimModel> getAllClaims();
}
