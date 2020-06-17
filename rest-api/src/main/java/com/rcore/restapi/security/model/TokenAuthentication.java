package com.rcore.restapi.security.model;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
public class TokenAuthentication implements Authentication {

    private AccessTokenDTO token;
    private String stringToken;
    private UserDTO user;
    private boolean authenticated;

    public TokenAuthentication(AccessTokenDTO token, String stringToken, boolean authenticated) {
        this.token = token;
        this.stringToken = stringToken;
        this.authenticated = authenticated;
    }

    public static Authentication ofToken(AccessTokenDTO token, String stringToken) {
        return new TokenAuthentication(token, stringToken, false);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles().stream()
                .map(RoleDTO::getName)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getLogin();
    }
}
