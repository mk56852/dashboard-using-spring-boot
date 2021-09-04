package com.dash.dash.controller;


import com.dash.dash.Service.SpecialityService;
import com.dash.dash.Service.UserService;
import com.dash.dash.Service.UserServiceImpl;
import com.dash.dash.domain.Speciality;
import com.dash.dash.domain.User;
import com.dash.dash.dto.ClientDto;
import com.dash.dash.dto.SpecialityDto;
import com.dash.dash.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService ;
    private SpecialityService specialityService ;


    @GetMapping("/find-all-friends")
    public ResponseEntity<Set<UserDto>> getAllFriends() {
        return ResponseEntity.ok(userService.findAllFriends());
    }

    @PostMapping("/add-speciality")
    public ResponseEntity addSpeciality(@RequestBody String specialityName){
        Speciality speciality = new Speciality(specialityName) ;
        if(!specialityService.isPresent(speciality.getName())) {
            specialityService.addSpeciality(speciality);
        }
        speciality =  specialityService.loadByName(specialityName) ;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userService.loadUserByUsername( (String)auth.getPrincipal()) ;

        specialityService.addUserToSpeciality(speciality.getId(),user);
        userService.addSpeciality(speciality);

    return ResponseEntity.ok("Speciality added ") ;

    }

}
