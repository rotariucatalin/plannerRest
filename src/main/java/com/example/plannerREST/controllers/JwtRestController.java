package com.example.plannerREST.controllers;

import com.example.plannerREST.entities.AuthRequest;
import com.example.plannerREST.entities.JwtToken;
import com.example.plannerREST.exception.ApiRequestException;
import com.example.plannerREST.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PreAuthorize("hasAuthority('View_Activity')")
    @GetMapping( value = "/hello")
    public String helloWorld() {

        return "Hello World!";

    }

    /*
        Method to create jwt token and send it to the frontend
     */
    @PostMapping(value = "/authenticate")
    public JwtToken generateToken(@RequestBody AuthRequest authRequest) throws ApiRequestException {

        UserDetails userDetails = authentication(authRequest.getUsername(), authRequest.getPassword());
        JwtToken jwtToken = new JwtToken(jwtUtil.createToken(userDetails,authRequest.getUsername()));

        return jwtToken;
    }

    /*
        Refresh token based on the actual token
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/refreshToken")
    public JwtToken refreshToken(@RequestBody String token) {

        JwtToken jwtToken = new JwtToken(jwtUtil.updateExpirationDateToken(token));

        return jwtToken;
    }

    /*
        Authenticate using the username and password from request, otherwise send an exception
     */
    private UserDetails authentication(String username, String password) throws ApiRequestException {

        try{

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

            return userDetails;

        } catch (Exception e) {

            throw new ApiRequestException("Username of password incorrect!");
        }
    }
}
