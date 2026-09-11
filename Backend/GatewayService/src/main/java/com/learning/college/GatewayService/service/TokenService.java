package com.learning.college.GatewayService.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.learning.college.GatewayService.dto.UserRequestDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	public String generateToken(UserRequestDTO user, int minutes) {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts
				.builder()
				.addClaims(claims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*minutes))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();	
	}

	private Key getKey() {
		String secretKey = "Y29sbGVnZW1hbmFnZW1lbnRzeXN0ZW1zZWN1cmVqd3R0b2tlbnNlY3JldGtleTIwMjY=";
		return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
	}

	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    } 

}
