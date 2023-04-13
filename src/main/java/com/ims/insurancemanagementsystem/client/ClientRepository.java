package com.ims.insurancemanagementsystem.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel,Long> {

    Optional<ClientModel> findByUserInfoId(Long id);
}
