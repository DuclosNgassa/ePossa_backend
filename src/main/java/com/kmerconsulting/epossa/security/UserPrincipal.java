package com.kmerconsulting.epossa.security;

import com.kmerconsulting.epossa.model.User;
import com.kmerconsulting.epossa.model.UserStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of roles (ROLE_name)
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPhone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus().equals(UserStatus.active);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals(UserStatus.active);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getStatus().equals(UserStatus.active);
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(UserStatus.active);
    }
}
