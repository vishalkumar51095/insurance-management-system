package com.ims.insurancemanagementsystem.client;

import com.ims.insurancemanagementsystem.Exception.MissingParameterException;
import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import com.ims.insurancemanagementsystem.user.UserInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<ClientModel> findAll();

    ClientModel findById(Long id) throws ResourceNotFoundException;

    ResponseEntity<?> createClient(Long userId) throws MissingParameterException;

    ResponseEntity<?> deleteById(Long id) ;


    ResponseEntity<?> addUser(UserInfo userInfo);

    ResponseEntity<?> updateClient(Long id, ClientModel updatedClient) throws ResourceNotFoundException;
}
