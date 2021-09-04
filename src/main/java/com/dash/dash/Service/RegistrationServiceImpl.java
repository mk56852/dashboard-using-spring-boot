package com.dash.dash.Service;

import com.dash.dash.util.RegistrationRequest;
import com.dash.dash.domain.User;
import com.dash.dash.domain.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl  implements RegistrationService{

    private final UserServiceImpl userServiceImpl;
    private final EmailValidator emailValidator ;


    @Override
    public void register(RegistrationRequest request) {
        boolean emailValid = emailValidator.test(request.getEmail()) ;
        if(!emailValid )
            throw new IllegalStateException("Email not valid") ;


        User newUser = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),"nn",null,true,false) ;
        userServiceImpl.signUp(newUser);
    }


}
