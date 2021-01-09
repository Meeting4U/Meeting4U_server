package com.projectMeeting4U.main.springboot.Security;

import com.projectMeeting4U.main.springboot.User.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("spring.jwt.secret")
    private String secretKey;

    private long tokenValidMillSecond = 1000L * 60 * 60; // 1Hour

    private final CustomUserDetailService userDetailsService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JwtTokenProvider(CustomUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() { // Initialize
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk, List<String> roles) { // Create JWT Token
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder().
                setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillSecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) { // Jwt token으로 인증 정보 조회
        System.out.println("#####");
        UserDetails userDetails =  userDetailsService.loadUserByUsername(this.getUserPk(token));
//                userRepository.findByUserId(this.getUserPk(token));
//                userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) { // Jwt token에서 회원 구별 정보 추출
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) { // Request에서 Header token Parsing
        return req.getHeader("X-AUTH-TOKEN");
    }

    public boolean validAtToken(String jwtToken) { // Jwt token 유효성과 만료일자 확인
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception err) {
            return false;
        }
    }



}
