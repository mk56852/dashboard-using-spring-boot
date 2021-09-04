package com.dash.dash.controller;


import com.dash.dash.Service.UserServiceImpl;
import com.dash.dash.domain.User;
import com.dash.dash.domain.chat.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin
public class ChatController {


     private SimpMessagingTemplate simpMessagingTemplate;
     private UserServiceImpl userServiceImpl;




    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable Long to, ChatMessage message) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) userServiceImpl.loadUserByUsername( (String)auth.getPrincipal()) ;
        User user = userServiceImpl.loadUserById(to) ;

        if (currentUser.isFriend(user)) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }


}
