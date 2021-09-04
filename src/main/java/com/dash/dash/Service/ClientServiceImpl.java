package com.dash.dash.Service;


import com.dash.dash.Repository.ClientRepository;
import com.dash.dash.domain.Client;
import com.dash.dash.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {


    private ClientRepository clientRepository ;
    private ModelMapper modelMapper ;



    @Override
    public ClientDto findClientByFullName(String FullName) {
        Optional<Client> client = clientRepository.findByFullName(FullName) ;
        if(client.isPresent())
            return modelMapper.map(client.get(), ClientDto.class) ;
        else
            throw new IllegalStateException("Client Not Found ") ;

    }

    @Override
    public ClientDto findClientById(Long id) {
        Optional<Client> client = clientRepository.findClientById(id) ;
        if(client.isPresent())
            return modelMapper.map(client.get(), ClientDto.class) ;
        else
            throw new IllegalStateException("Client Not Found ") ;

     }

    @Override
    public Client loadByFullName(String fullName) {
        Optional<Client> client = clientRepository.findByFullName(fullName);
        if(client.isPresent())
            return client.get() ;
        else
            throw new IllegalStateException("Client Not Found");

    }


    @Override
    public  void addClient(Client client)
    {
        clientRepository.save(client) ;

    }
    @Override
    public void deleteClient(String fullName)
    {
        Optional<Client> client = clientRepository.findByFullName(fullName);
        if(client.isPresent())
            clientRepository.delete(client.get());
        else
            throw new IllegalStateException("Client Not Found");

    }

    @Override
    public void update(String fullName , Client updatedClient)
    {
        Optional<Client> client = clientRepository.findByFullName(fullName);
        if(client.isPresent()) {
            updatedClient.setId(client.get().getId());
            clientRepository.save(updatedClient);
        }
        else
            throw new IllegalStateException("Client Not Found");
    }


}
