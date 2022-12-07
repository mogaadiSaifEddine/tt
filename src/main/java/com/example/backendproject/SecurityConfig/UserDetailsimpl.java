package com.example.backendproject.SecurityConfig;

import com.example.backendproject.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsimpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;
    private String passwod;


    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsimpl(Long id, String username,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }
    public UserDetailsimpl(Long id, String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.passwod = password;
        this.authorities = authorities;
    }

    public static UserDetailsimpl build(User user) {

        List<GrantedAuthority> authorities =new ArrayList<>();
//                user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRole()))
//                .collect(Collectors.toList());
    authorities.add( new SimpleGrantedAuthority(user
            .getRole().getRole()));
        return new UserDetailsimpl(
                user.getUser_id(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String getPassword() {
        return passwod;
    }

    @Override
    public String getUsername() {
        return username;
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsimpl user = (UserDetailsimpl) o;
        return Objects.equals(id, user.id);
    }
}
