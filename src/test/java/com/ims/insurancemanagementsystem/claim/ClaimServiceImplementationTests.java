package com.ims.insurancemanagementsystem.claim;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import com.ims.insurancemanagementsystem.insurancepolicy.InsurancePolicyModel;
import com.ims.insurancemanagementsystem.insurancepolicy.InsurancePolicyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClaimServiceImplementationTests {


    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private InsurancePolicyRepository insurancePolicyRepository;

    @InjectMocks
    private ClaimServiceImplementation claimServiceImplementation;

    public void ClaimServiceImplementationTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllClaims() {
        List<ClaimModel> claims = new ArrayList<>();
        claims.add(new ClaimModel());
        claims.add(new ClaimModel());

        when(claimRepository.findAll()).thenReturn(claims);

        List<ClaimModel> result = claimServiceImplementation.getAllClaims();

        assertEquals(2, result.size());
    }

    @Test
    void testGetClaimById() throws ResourceNotFoundException {
        Long id = 1L;
        ClaimModel claim = new ClaimModel();
        claim.setId(id);

        when(claimRepository.findById(anyLong())).thenReturn(java.util.Optional.of(claim));

        ClaimModel result = claimServiceImplementation.getClaimById(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testCreateClaim() throws ResourceNotFoundException {
        ClaimDto claimDto = new ClaimDto();
        claimDto.setDescription("Test Description");
        claimDto.setClaimDate(LocalDate.parse("2022-01-01"));
        claimDto.setClaimStatus("Pending");
        claimDto.setPolicyId(1L);

        InsurancePolicyModel policy = new InsurancePolicyModel();
        policy.setId(claimDto.getPolicyId());

        when(insurancePolicyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(policy));
        when(claimRepository.save(any(ClaimModel.class))).thenReturn(new ClaimModel());

        ResponseEntity<?> responseEntity = claimServiceImplementation.createClaim(claimDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateClaim() throws ResourceNotFoundException {
        Long id = 1L;
        ClaimDto claimDto = new ClaimDto();
        claimDto.setDescription("Updated Description");
        claimDto.setClaimDate(LocalDate.parse("2022-02-01"));
        claimDto.setClaimStatus("Approved");
        claimDto.setPolicyId(1L);

        ClaimModel claim = new ClaimModel();
        claim.setId(id);

        InsurancePolicyModel policy = new InsurancePolicyModel();
        policy.setId(claimDto.getPolicyId());

        when(claimRepository.findById(anyLong())).thenReturn(java.util.Optional.of(claim));
        when(insurancePolicyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(policy));
        when(claimRepository.save(any(ClaimModel.class))).thenReturn(new ClaimModel());

        ResponseEntity<?> responseEntity = claimServiceImplementation.updateClaim(id, claimDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteClaim() {
        Long id = 1L;

        ResponseEntity<?> responseEntity = claimServiceImplementation.deleteClaim(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
