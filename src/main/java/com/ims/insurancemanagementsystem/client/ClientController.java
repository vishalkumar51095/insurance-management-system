package com.ims.insurancemanagementsystem.client;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<ClientModel> getAllClients() {
        return clientService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ClientModel getClientById(@PathVariable Long id) throws ResourceNotFoundException {

        return clientService.findById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel createClient(@RequestBody ClientModel client) {
        return clientService.createClient(client);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientModel updatedClient) throws ResourceNotFoundException {
        return clientService.updateClient(id,updatedClient);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        return clientService.deleteById(id);
    }
}
