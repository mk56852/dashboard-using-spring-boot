package com.dash.dash.Service;

import com.dash.dash.domain.Client;
import com.dash.dash.domain.File;
import com.dash.dash.domain.Speciality;
import com.dash.dash.domain.User;
import com.dash.dash.dto.ClientDto;
import com.dash.dash.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

     void signUp(User user) ;
     User loadUserById(Long id) ;
     Set<ClientDto> findAllClients() ;
     Set<UserDto>findAllFriends() ;
     int enableAppUser(String email);
     void addClient(Client client) ;
     void addFriendWithEmail(String email) ;
     void addFriendWithUserName(String fullName) ;
     void addFile(File file) ;
     void addSpeciality(Speciality speciality) ;
     void deleteClient(String fullName) ;
     User loadUserByResetPasswordToken(String token) ;
     void updateResetPasswordToken(String token , String email);
     void updatePassword(User user , String newPassword) ;

}
