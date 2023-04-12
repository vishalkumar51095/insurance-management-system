package com.ims.insurancemanagementsystem.client;

import com.ims.insurancemanagementsystem.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<ClientModel> getAllClients() {
        return clientService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ClientModel getClientById(@PathVariable Long id) throws ResourceNotFoundException {

        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel createClient(@RequestBody ClientModel client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public ClientModel updateClient(@PathVariable Long id, @RequestBody ClientModel updatedClient) throws ResourceNotFoundException {
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) throws ResourceNotFoundException {
        clientService.deleteById(id);
    }
}
