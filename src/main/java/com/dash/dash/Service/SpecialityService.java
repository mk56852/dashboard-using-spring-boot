package com.dash.dash.Service;

import com.dash.dash.domain.Speciality;
import com.dash.dash.domain.User;
import com.dash.dash.dto.SpecialityDto;
import com.dash.dash.dto.UserDto;

import java.util.Set;

public interface SpecialityService {

    void addSpeciality(Speciality speciality) ;

    Speciality loadById(Long id) ;
    Speciality loadByName(String name) ;

    boolean isPresent(Long id);
    boolean isPresent(String name) ;

    SpecialityDto getById(Long id) ;
    SpecialityDto getByName(String name) ;
    Set<UserDto> getAllUsers(Long id) ;

    void addUserToSpeciality(Long id , User user) ;
}
