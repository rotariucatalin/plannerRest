package com.example.plannerREST.entities;

import java.io.Serializable;

public class JwtToken implements Serializable {

    private final String jwtToken;

    public JwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
