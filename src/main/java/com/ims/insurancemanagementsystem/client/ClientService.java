package com.ims.insurancemanagementsystem.client;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import com.ims.insurancemanagementsystem.user.UserInfo;

import java.util.List;

public interface ClientService {
    List<ClientModel> findAll();

    ClientModel findById(Long id) throws ResourceNotFoundException;

    ClientModel createClient(ClientModel client);

    void deleteById(Long id) throws ResourceNotFoundException;


    String addUser(UserInfo userInfo);
}
