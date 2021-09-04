package com.dash.dash.controller;


import com.dash.dash.Service.ClientService;
import com.dash.dash.Service.UserService;
import com.dash.dash.domain.Client;
import com.dash.dash.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;
    private UserService userService ;

    @GetMapping("/find-by-id/{clientId}")
    public ResponseEntity<ClientDto> getClientById(
            @PathVariable(name = "clientId", required = true) Long clientId) throws Exception {
        return ResponseEntity.ok(clientService.findClientById(clientId));
    }

    @GetMapping("/find-by-fullName/{fullName}")
    public ResponseEntity<ClientDto> getClientByFullName(
            @PathVariable(name = "fullName", required = true) String fullName) throws Exception {
        return ResponseEntity.ok(clientService.findClientByFullName(fullName));
    }

    @GetMapping("/find-all-clients")
    public ResponseEntity<Set<ClientDto>> getAllClients() {
        return ResponseEntity.ok(userService.findAllClients());
    }

}
