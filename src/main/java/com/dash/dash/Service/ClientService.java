package com.dash.dash.Service;

import com.dash.dash.domain.Client;
import com.dash.dash.dto.ClientDto;

public interface ClientService {

     Client loadByFullName(String fullName);
     ClientDto findClientByFullName(String FullName);
     ClientDto findClientById(Long id) ;
     void addClient(Client client) ;
     void deleteClient(String fullName) ;
     void update (String fullName,Client updatedClient);
}
