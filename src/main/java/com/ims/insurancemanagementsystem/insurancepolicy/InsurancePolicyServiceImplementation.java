package com.ims.insurancemanagementsystem.insurancepolicy;

import com.ims.insurancemanagementsystem.client.ClientModel;
import com.ims.insurancemanagementsystem.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InsurancePolicyServiceImplementation implements InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private  ClientRepository clientRepository;

    @Override
    public List<InsurancePolicyModel> findAll() {
        return insurancePolicyRepository.findAll();
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        HashMap<String,Object> map=new HashMap<>();
        Optional<InsurancePolicyModel> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            return ResponseEntity.ok(optionalPolicy.get());
        } else {
            map.put("message","Insurance Policy is not found");
            return ResponseEntity.status(404).body(map);
        }
    }

    @Override
    public ResponseEntity<?> createPolicy(InsurancePolicyDto insurancePolicyDto) {
        return setInsurancePolicyData(insurancePolicyDto, new InsurancePolicyModel());
    }

    private ResponseEntity<?> setInsurancePolicyData(InsurancePolicyDto insurancePolicyDto, InsurancePolicyModel insurancePolicy) {
        Optional<ClientModel> optionalClient = clientRepository.findById(insurancePolicyDto.getClientId());
        if (optionalClient.isPresent()) {
            insurancePolicy.setPolicyNumber(insurancePolicyDto.getPolicyNumber());
            insurancePolicy.setType(insurancePolicyDto.getType());
            insurancePolicy.setCoverageAmount(insurancePolicyDto.getCoverageAmount());
            insurancePolicy.setPremium(insurancePolicyDto.getPremium());
            insurancePolicy.setStartDate(insurancePolicyDto.getStartDate());
            insurancePolicy.setEndDate(insurancePolicyDto.getEndDate());
            insurancePolicy.setClient(optionalClient.get());

            InsurancePolicyModel createdPolicy = insurancePolicyRepository.save(insurancePolicy);
            return ResponseEntity.ok(createdPolicy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> updatePolicy(Long id, InsurancePolicyDto insurancePolicyDto) {
        Optional<InsurancePolicyModel> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            return setInsurancePolicyData(insurancePolicyDto, optionalPolicy.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        HashMap<String,Object> map=new HashMap<>();
        Optional<InsurancePolicyModel> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            insurancePolicyRepository.deleteById(id);
            map.put("message","delete successfully");
        } else {
            map.put("message","Policy id is not found");
            return ResponseEntity.status(404).body(map);
        }
        return ResponseEntity.ok(map);
    }
}
