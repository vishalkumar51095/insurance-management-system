package com.ims.insurancemanagementsystem.client;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import com.ims.insurancemanagementsystem.user.UserInfo;
import com.ims.insurancemanagementsystem.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientModel findById(Long id) throws ResourceNotFoundException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }

    @Override
    public ClientModel createClient(ClientModel client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with id " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "saved used successfully";

    }
}

