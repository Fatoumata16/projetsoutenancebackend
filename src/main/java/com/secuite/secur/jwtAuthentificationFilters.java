package com.secuite.secur;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class jwtAuthentificationFilters extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public jwtAuthentificationFilters(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    //c'est la methode qui va nous servire de recuperer les donnees de l'utilisateur
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        //retour d'un objet authntication qui contient les donnees de l'utilisateur
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override

    //l'authentification a reussit
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("succesfullAuthantication");
        User user= (User) authResult.getPrincipal();
        Algorithm algorithm=Algorithm.HMAC256("mySecret1234");
        String jwtAccessToken= (JWT.create().withSubject(user.getUsername()).withClaim("role", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList())).withExpiresAt(new Date(System.currentTimeMillis() + 5*60 *1000)).withIssuer(request.getRequestURL().toString()).sign(algorithm));
        String jwtRefrechToken= (JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 15*60 * 1000)).withIssuer(request.getRequestURL().toString()).sign(algorithm));
        Map<String,String> idToken=new HashMap<>();
        idToken.put("access-token",jwtAccessToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);







    }
}
