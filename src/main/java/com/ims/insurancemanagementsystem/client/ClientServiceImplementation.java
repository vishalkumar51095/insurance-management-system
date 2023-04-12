package com.ims.insurancemanagementsystem.client;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import com.ims.insurancemanagementsystem.user.UserInfo;
import com.ims.insurancemanagementsystem.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public ResponseEntity<?> deleteById(Long id)  {
        HashMap<Object,String> map=new HashMap<>();
        try {
            if (!clientRepository.existsById(id)) {
                map.put("message","Client not found with id " + id);
                return ResponseEntity.status(404).body(map);
            }
            else {
                clientRepository.deleteById(id);
                map.put("message", "delete successfully");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> addUser(UserInfo userInfo){
        HashMap<String,Object> map=new HashMap<>();
        try {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            map.put("message","saved used successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(map);

    }

    @Override
    public ResponseEntity<?> updateClient(Long id, ClientModel updatedClient) {
        HashMap<String,Object> map=new HashMap<>();
        try {
            ClientModel client = clientRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
            client.setName(updatedClient.getName());
            client.setDateOfBirth(updatedClient.getDateOfBirth());
            client.setAddress(updatedClient.getAddress());
            client.setContactInformation(updatedClient.getContactInformation());
            clientRepository.save(client);
            map.put("message", "Client update successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(map);
    }
}

