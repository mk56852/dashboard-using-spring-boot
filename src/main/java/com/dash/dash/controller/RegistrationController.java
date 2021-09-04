package com.dash.dash.controller;



import com.dash.dash.Service.RegistrationService;
import com.dash.dash.Service.UserService;
import com.dash.dash.Service.UserServiceImpl;
import com.dash.dash.util.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;




    @PostMapping("login")
    public ResponseEntity ok()
    {
        return ResponseEntity.ok("welcome") ;
    }


    @PostMapping("registration")
    public ResponseEntity register(@RequestBody RegistrationRequest request)
    {
        registrationService.register(request) ;
        return ResponseEntity.ok("saved") ;
    }


}
