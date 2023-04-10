package com.ims.insurancemanagementsystem.claim;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import com.ims.insurancemanagementsystem.insurancepolicy.InsurancePolicyModel;
import com.ims.insurancemanagementsystem.insurancepolicy.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ClaimServiceImplementation implements ClaimService{
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public List<ClaimModel> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public ClaimModel getClaimById(Long id) throws ResourceNotFoundException {
        return claimRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Claim is not found"+id));
    }

    @Override
    public ResponseEntity<?> createClaim(ClaimDto claimDto) throws ResourceNotFoundException {
        InsurancePolicyModel policy = insurancePolicyRepository.findById(claimDto.getPolicyId()).orElseThrow(() -> new ResourceNotFoundException("Policy is not available" + claimDto.getPolicyId()));
        HashMap<String,Object> map=new HashMap<>();
        try {
            ClaimModel claim = new ClaimModel();
            claim.setDescription(claimDto.getDescription());
            claim.setClaimDate(claimDto.getClaimDate());
            claim.setClaimStatus(claimDto.getClaimStatus());
            claim.setInsurancePolicy(policy);
            claimRepository.save(claim);
            map.put("message","Data saved successfully");
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> updateClaim(Long id, ClaimDto claimDto) throws ResourceNotFoundException {
        HashMap<String,Object> map= new HashMap<>();
        ClaimModel claim = claimRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Claim is not found" + id));

        InsurancePolicyModel policy = insurancePolicyRepository.findById(claimDto.getPolicyId()).orElseThrow(() -> new ResourceNotFoundException("Policy is not present" + claimDto.getPolicyId()));

        try {
            claim.setDescription(claimDto.getDescription());
            claim.setClaimDate(claimDto.getClaimDate());
            claim.setClaimStatus(claimDto.getClaimStatus());
            claim.setInsurancePolicy(policy);
            claimRepository.save(claim);
            map.put("message","Data saved successfully");
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> deleteClaim(Long id) {
        HashMap<String,Object> map=new HashMap<>();
        claimRepository.deleteById(id);
        map.put("message","claim delete successfully");

        return ResponseEntity.ok(map);
    }
}
