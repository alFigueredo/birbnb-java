package com.panki.birbnb_backend.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.model.enums.TipoUsuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration = 604800000l;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(Long id, String name, String email, TipoUsuario tipoUsuario) {
		final Instant now = Instant.now();
		final Instant expiryDate = now.plusMillis(expiration);
		return Jwts.builder()
				.subject(id.toString()).claim("name", name)
				.claim("email", email).claim("type", tipoUsuario).issuedAt(Date.from(now))
				.expiration(Date.from(expiryDate))
				.signWith(getSigningKey()).compact();
	}

	public boolean validateToken(String token) {
		final Optional<Jws<Claims>> claim = Optional
				.of(Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token));
		return claim.isPresent();
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	public String extractUserId(String token) {
		return extractClaims(token).getSubject();
	}

	public String extractName(String token) {
		return extractClaims(token).get("name", String.class);
	}

	public String extractEmail(String token) {
		return extractClaims(token).get("email", String.class);
	}

	public TipoUsuario extractType(String token) {
		return extractClaims(token).get("Type", TipoUsuario.class);
	}
}
