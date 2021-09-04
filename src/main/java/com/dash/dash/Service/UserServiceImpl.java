package com.dash.dash.Service;



import com.dash.dash.Repository.FileRepository;
import com.dash.dash.Repository.SpecialityRepository;
import com.dash.dash.Repository.UserRepository;
import com.dash.dash.domain.Client;
import com.dash.dash.domain.File;
import com.dash.dash.domain.Speciality;
import com.dash.dash.domain.User;
import com.dash.dash.dto.ClientDto;
import com.dash.dash.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService,UserService {

    private final UserRepository userRepository ;
    private final SpecialityRepository specialityRepository ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;
    private final ClientServiceImpl clientServiceImpl;
    private FileRepository fileRepository ;
    private ModelMapper modelMapper ;






    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            User userDetails = userRepository.findByEmail(email) ;
            if(userDetails == null )
                throw new UsernameNotFoundException(String.format("User with Email %s  Not Found" , email)) ;
            else {
                userDetails.setLocked(false);
                userDetails.setEnable(true);
                return userDetails;
                }

    }

    @Override
    public void addFile(File file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;
        user.addFile(file) ;
        file.addUser(user);
        fileRepository.save(file);
        userRepository.save(user) ;

    }

    public User loadUserById(Long id)
    {
        return  userRepository.findUserById(id) ;
    }

    @Override
    public Set<ClientDto> findAllClients() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;

        Set<Client> clients = user.getClients() ;
        Set<ClientDto> clientDto = new HashSet<>();
        clients.forEach(client -> clientDto.add(modelMapper.map(client,ClientDto.class)));
        return  clientDto;



    }


    @Override
    public Set<UserDto> findAllFriends() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;
        Set<User> friends = user.getFriends() ;
        Set<UserDto> friendsDto = new HashSet<>();
        friends.forEach(friend -> friendsDto.add(modelMapper.map(friend,UserDto.class)));
        return  friendsDto;
    }

    @Override
    public void addSpeciality(Speciality speciality) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;

        Set<Speciality> specialities = user.getSpecialities() ;
        if (! specialityRepository.existsById(speciality.getId()))
            specialityRepository.save(speciality);
        if(specialities.size()<4)
        {

            user.addSpecility(speciality);
            userRepository.save(user) ;


        }
        else
            throw new IllegalStateException("can't add more speciality") ;
    }

    @Override

    public void signUp(User user)
    {
        User user1 = userRepository.findByEmail(user.getEmail()) ;

        if(user1 != null)
            throw  new IllegalStateException("Email Already taken") ;
        String encodedPassword =  bCryptPasswordEncoder.encode(user.getPassword()) ;
       user.setPassword(encodedPassword);
       userRepository.save(user) ;
     }

    @Override
    public int enableAppUser(String email) {
        return userRepository.enableUser(email);
    }



    // Reset Password

    @Override
    public void updateResetPasswordToken(String token , String email)
    {
        User user = userRepository.findByEmail(email) ;
        if(user == null )
            throw new IllegalStateException("User Not Found") ;
        else
            user.setResetPasswordToken(token);
            userRepository.save(user) ;

    }

    @Override

    public User loadUserByResetPasswordToken(String token)
    {
        User user = userRepository.findByResetPasswordToken(token);
        return user ;
    }

    @Override

    public void updatePassword(User user , String newPassword)
    {
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword) ;
        user.setPassword(encryptedPassword) ;
        user.setResetPasswordToken(null);
        userRepository.save(user) ;
        }



        // Add New Client
    @Override
    public void addClient(Client client)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;
        user.setClient(client);
        userRepository.save(user) ;


    }
    @Override
    public void deleteClient(String fullName)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;
        Client client = clientServiceImpl.loadByFullName(fullName);
        user.getClients().remove(client) ;
        clientServiceImpl.deleteClient(fullName);
    }



        // Add Friends

    @Override
    public void addFriendWithEmail(String email)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;
        User newFriend = userRepository.findByEmail(email) ;

        if (newFriend == null)
            return ;


        user.setFriend(newFriend);
        newFriend.setFriend(user);
        userRepository.save(user);
        userRepository.save(newFriend);


    }

    @Override
    public void addFriendWithUserName(String userName)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loadUserByUsername( (String)auth.getPrincipal()) ;
        User newFriend = userRepository.findByUserName(userName);

        if (newFriend == null)
            return ;

        user.setFriend(newFriend);
        newFriend.setFriend(user);
        userRepository.save(user);
        userRepository.save(newFriend);


    }


}
