package com.panki.birbnb_backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.panki.birbnb_backend.model.enums.TipoUsuario;
import com.panki.birbnb_backend.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	public JwtAuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		final String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {

			final String token = authHeader.substring(7);

			if (jwtService.validateToken(token)) {
				final String userId = jwtService.extractUserId(token);
				final TipoUsuario type = jwtService.extractType(token);
				final List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + type));
				final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userId, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);

	}
}
