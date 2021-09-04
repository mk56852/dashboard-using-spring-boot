package com.dash.dash.security.jwt;

import com.dash.dash.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager ;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        try {
            AuthenticationRequest authentication = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationRequest.class);

            Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getEmail(),authentication.getPassword()) ;
            return authenticationManager.authenticate(auth) ;

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

         String key = "Melek56852SecuritykeyforthisappMelek56852SecuritykeyforthisappMelek56852Securitykeyforthisapp" ;
         User user  = (User)authResult.getPrincipal();
         String token = Jwts.builder().setSubject(user.getEmail())
                 .setIssuedAt(new Date())
                 .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
                 .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                 .compact() ;


         response.addHeader("Authorization","Bearer "+token);
        SecurityContextHolder.getContext().setAuthentication(authResult);
         chain.doFilter(request,response);
    }
}
