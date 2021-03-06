package com.korbiak.api.security.jwt;

import com.korbiak.api.model.Employee;
import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@Data
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(Employee employee) {
        log.info("Creating token for user = " + employee.getFirstName());
        Claims claims = Jwts.claims().setSubject(employee.getFirstName() + "_" + employee.getSecondName());
        String role = null;
        if (employee.getIsAdmin() == 1) {
            role = "USER";
        } else if (employee.getIsAdmin() == 2) {
            role = "ADMIN";
        } else if (employee.getIsAdmin() == 3) {
            role = "MAIN_ADMIN";
        }
        claims.put("roles", role);
        if (employee.getDepartment() != null){
            claims.put("departmentId", employee.getDepartment().getId());
        }
        claims.put("userId", employee.getId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        log.info("Creating authentication for token");
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,
                "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        log.info("Resolving token");
        String bearer = "Bearer_";
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(bearer)) {
            return bearerToken.substring(bearer.length());
        }
        log.warn("Resolving token return null");
        return null;
    }

    public boolean validateToken(String token) {
        log.info("Validating token");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Token is not valid");
            return false;
        }
    }
}
