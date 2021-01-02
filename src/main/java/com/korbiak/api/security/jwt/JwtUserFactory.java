package com.korbiak.api.security.jwt;

import com.korbiak.api.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public final class JwtUserFactory {

    public static JwtUser create(Employee user) {
        return new JwtUser(
                user.getId(),
                user.getFirstName() + "_" + user.getSecondName(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getIsAdmin())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(int adminPermissions) {
        SimpleGrantedAuthority authority = null;
        if (adminPermissions == 1) {
            authority = new SimpleGrantedAuthority("USER");
        } else if (adminPermissions == 2) {
            authority = new SimpleGrantedAuthority("ADMIN");
        } else if (adminPermissions == 3) {
            authority = new SimpleGrantedAuthority("MAIN_ADMIN");
        }
        return Collections.singletonList(authority);
    }
}
