package com.ims.insurancemanagementsystem.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");

//    Bearer   eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJWaXNoYWwiLCJpYXQiOjE2ODA2Nzk4NTAsImV4cCI6MTY4MDY4MzQ1MH0.QQeB6UGoZiS7rLyxIiSdgLD6VDx2z2hKqFF6px-0jCk
        String token=null;
        String username=null;
        if(authHeader!=null &&authHeader.startsWith("Bearer")){
            token=authHeader.substring(7); //step 1 : extract token
            username= jwtService.extractUsername(token);// step 2: extract username
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails)){ // step 3: validated token is expired or not
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());// step 4: create auth object
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);//step 5: set auth object into security context holder
            }
        }
        filterChain.doFilter(request,response);
    }


}
