package com.example.plannerREST.security;

import com.example.plannerREST.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private Users users;

    public User(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        users.getPermissions().forEach(permission -> {

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());

            grantedAuthorities.add(grantedAuthority);
        });

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return users.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return users.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return users.isActive();
    }

    @Override
    public boolean isEnabled() {
        return users.isActive();
    }
}
