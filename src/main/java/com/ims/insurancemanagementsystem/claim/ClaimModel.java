package com.ims.insurancemanagementsystem.claim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ims.insurancemanagementsystem.insurancepolicy.InsurancePolicyModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="claims")
public class ClaimModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="claim_number")
    private Long claimNumber;
    private String description;

    @Column(name="calim_date")
    private LocalDate claimDate;

    @Column(name="claim_status")
    private String claimStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "insurance_policy_id")
    private InsurancePolicyModel insurancePolicy;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Long claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public InsurancePolicyModel getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicyModel insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
