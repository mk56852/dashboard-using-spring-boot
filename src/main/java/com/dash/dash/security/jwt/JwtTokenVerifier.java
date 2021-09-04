package com.dash.dash.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@NoArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if(pathInfo != "/registration")
        {
            String authHeader = request.getHeader("Authorization") ;
            String key = "Melek56852SecuritykeyforthisappMelek56852SecuritykeyforthisappMelek56852Securitykeyforthisapp" ;

            if( authHeader == null )
            {
                filterChain.doFilter(request,response);
                return ;}
            try
            {
                String token = authHeader.replace("Bearer ","");


               Jws<Claims> ClaimsJws =Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token) ;
                Claims body = ClaimsJws.getBody() ;
                String username = body.getSubject() ;
                Authentication auth = new UsernamePasswordAuthenticationToken(username,null,null) ;
                SecurityContextHolder.getContext().setAuthentication(auth);
        }
         catch (JwtException e)
            {
                throw new IllegalStateException("token is not valid ") ;
            }
            filterChain.doFilter(request,response);
            }
        }
}
