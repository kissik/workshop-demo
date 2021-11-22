package edu.kpi.iasa.mmsa.workshop.controller;

import edu.kpi.iasa.mmsa.workshop.dto.JwtRequestDto;
import edu.kpi.iasa.mmsa.workshop.dto.JwtResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class UserController {
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto jwtRequestDto) {

        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setToken(getJWTToken(jwtRequestDto));

        return ResponseEntity.ok(jwtResponseDto);
    }

    private String getJWTToken(JwtRequestDto jwtRequestDto) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(jwtRequestDto.getLogin())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
