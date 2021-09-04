package com.dash.dash.controller;



import com.dash.dash.Service.ClientService;
import com.dash.dash.Service.EmailValidator;
import com.dash.dash.Service.UserService;
import com.dash.dash.domain.Client;
import com.dash.dash.dto.ClientDto;
import com.dash.dash.util.AddClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor

public class HomePageController {

    private UserService userService;
    private ClientService clientService;
    private  EmailValidator emailValidator ;


    @PostMapping(path = "/add-client")
    public ResponseEntity addClient(@RequestBody AddClientRequest request)
    {
        Client client = new Client(request.getFullName(),new Date(),request.getStatus(),request.getDisease(),request.getPhoneNumber());
        clientService.addClient(client);
        userService.addClient(client);
        return ResponseEntity.ok("New client added") ;

    }




    @PostMapping(path = "/add-friend")
    public ResponseEntity addFriend(@RequestBody String request)
    {
        if(emailValidator.test(request))
            userService.addFriendWithEmail(request);

        else
            userService.addFriendWithUserName(request);

        return ResponseEntity.ok(" New Friend added") ;

    }


    @DeleteMapping("/{fullName}")
    public ResponseEntity deleteClient(@PathVariable String fullName)
    {
        userService.deleteClient(fullName);
        return ResponseEntity.ok("deleted") ;
    }

    @PutMapping("/{fullName}")
    public ResponseEntity updateClient(@PathVariable String fullName ,@RequestBody Client client)
    {
        clientService.update(fullName,client);
        return ResponseEntity.ok("updated")  ;
    }







}
