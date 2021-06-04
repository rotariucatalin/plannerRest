package com.example.plannerREST.filter;

import com.example.plannerREST.entities.CustomException;
import com.example.plannerREST.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException {

        try {

            String authorizationHeader = httpServletRequest.getHeader("Authorization");
            String token = null;
            String username = null;

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                token = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(token);
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                if(!jwtUtil.isTokenExpired(token)) {

                    Claims claims = jwtUtil.extractAllClaims(token);
                    List<String> authorities = claims.get("authorities", ArrayList.class);

                    List<SimpleGrantedAuthority> roleList = new ArrayList<>();
                    if (authorities != null && !authorities.isEmpty()) {
                        roleList = authorities.stream()
                                .map(role -> new SimpleGrantedAuthority(role))
                                .collect(Collectors.toList());
                    }

                    username = claims.getSubject();

                    if (username != null) {
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, roleList));
                    }
                }
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } catch (ExpiredJwtException expiredJWTException) {

            CustomException customException = new CustomException("Token expired!",INTERNAL_SERVER_ERROR);

            httpServletResponse.setStatus(INTERNAL_SERVER_ERROR.value());
            httpServletResponse.getWriter().write(convertObjectToJson(customException));

        } catch (JwtException jwtException) {

            CustomException customException = new CustomException("Unable to read JWT token",INTERNAL_SERVER_ERROR);

            httpServletResponse.setStatus(INTERNAL_SERVER_ERROR.value());
            httpServletResponse.getWriter().write(convertObjectToJson(customException));

        } catch (BadCredentialsException badCredentialsExceptions) {

            CustomException customException = new CustomException("Bad credentials!",INTERNAL_SERVER_ERROR);

            httpServletResponse.setStatus(INTERNAL_SERVER_ERROR.value());
            httpServletResponse.getWriter().write(convertObjectToJson(customException));

        } catch (SQLGrammarException e) {

            CustomException customException = new CustomException(e.getMessage(), BAD_REQUEST);

            httpServletResponse.setStatus(BAD_REQUEST.value());
            httpServletResponse.getWriter().write(convertObjectToJson(customException));

        } catch (GenericJDBCException e) {

            CustomException customException = new CustomException(e.getMessage(), BAD_REQUEST);

            httpServletResponse.setStatus(BAD_REQUEST.value());
            httpServletResponse.getWriter().write(convertObjectToJson(customException));

        } catch (Exception e) {

            CustomException customException = new CustomException(e.getMessage(), BAD_REQUEST);

            httpServletResponse.setStatus(BAD_REQUEST.value());
            httpServletResponse.getWriter().write(convertObjectToJson(customException));

        }

    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {

        if (object == null) return null;

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(object);
    }
}
