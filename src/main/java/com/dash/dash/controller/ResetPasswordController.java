package com.dash.dash.controller;

import com.dash.dash.Service.UserServiceImpl;
import com.dash.dash.domain.User;
import com.dash.dash.util.NewPasswordResetRequest;
import com.dash.dash.util.PasswordResetRequest;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.IllegalWriteException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@RestController
@AllArgsConstructor

public class ResetPasswordController {

    private UserServiceImpl userServiceImpl;
    private JavaMailSender mailSender ;



    @PostMapping("/login/reset_password")
    public ResponseEntity resetPassword(@RequestBody PasswordResetRequest request) throws MessagingException {

        if(userServiceImpl.loadUserByUsername(request.getEmail()) != null)
        {
            String token = RandomString.make(40) ;
            userServiceImpl.updateResetPasswordToken(token, request.getEmail());
            String resetPasswordLink = null;
            resetPasswordLink = "http://localhost:8080/login/reset_password_process?token=" + token;
            sendMail(request.getEmail(),resetPasswordLink) ;


            return ResponseEntity.ok("Mail sended succesfully ") ;

        }
        else
            throw new IllegalWriteException("user not found") ;
    }

    private void sendMail(String email, String resetPasswordLink) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage() ;
        MimeMessageHelper helper = new MimeMessageHelper(message) ;

        helper.setFrom("Contact@gmail.com") ;
        helper.setTo(email) ;
        helper.setSubject("Here's the link to reset your password") ;

        String text = "<p>You have requested to reset your password </p>"+
                "<p>click the link below to change your password </p> " +
                "<p><a href = "+resetPasswordLink+"> Change my password </a> </p>" ;

        helper.setText(text,true);
        mailSender.send(message) ;
    }




    @PostMapping("/login/reset_password_process/**")
    public String changePassword(@Param(value="token") String token, @RequestBody  NewPasswordResetRequest request)
    {
        System.out.println(token);
        User user = userServiceImpl.loadUserByResetPasswordToken(token) ;
        if(  user != null ) {
            userServiceImpl.updatePassword(user,request.getNewPassword());
            return "password changed";
        }
        else
            return "" ;
    }


}


