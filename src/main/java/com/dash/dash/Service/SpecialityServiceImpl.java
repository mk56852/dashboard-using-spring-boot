package com.dash.dash.Service;

import com.dash.dash.Repository.SpecialityRepository;
import com.dash.dash.domain.Speciality;
import com.dash.dash.domain.User;
import com.dash.dash.dto.SpecialityDto;
import com.dash.dash.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor

public class SpecialityServiceImpl implements SpecialityService{

    private SpecialityRepository specialityRepository ;
    private ModelMapper modelMapper ;

    @Override
    public Speciality loadById(Long id) {
        return specialityRepository.findSpecialityById(id);
    }

    @Override
    public Speciality loadByName(String name) {
        return specialityRepository.findSpecialityByName(name);
    }

    @Override
    public SpecialityDto getById(Long id) {
        Speciality speciality = loadById(id) ;
        return  modelMapper.map(speciality,SpecialityDto.class) ;
    }

    @Override
    public SpecialityDto getByName(String name) {
        Speciality speciality = loadByName(name) ;
        return  modelMapper.map(speciality,SpecialityDto.class) ;
    }

    @Override
    public Set<UserDto> getAllUsers(Long id) {
        Speciality speciality = loadById(id) ;
        Set<User> users = speciality.getUsers() ;
        Set<UserDto> usersDto = new HashSet<>() ;
        users.forEach(user -> usersDto.add(modelMapper.map(user,UserDto.class)));
        return usersDto ;
    }

    @Override
    public boolean isPresent(Long id) {

        if ( !specialityRepository.existsById(id))
            return false ;
        return true ;
    }

    @Override
    public boolean isPresent(String name) {
        Speciality speciality = loadByName(name) ;
        if (speciality == null)
            return false ;
        return true ;
    }

    @Override
    public void addSpeciality(Speciality speciality) {
        specialityRepository.save(speciality);
    }


    @Override
    public void addUserToSpeciality(Long id ,User user) {
        Speciality speciality = loadById(id) ;
        speciality.addUser(user);
        specialityRepository.save(speciality) ;

    }
}
