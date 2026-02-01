package com.panki.birbnb_backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.service.JwtService;
import com.panki.birbnb_backend.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final UsuarioService usuarioService;
	private final JwtService jwtService;

	public OAuth2LoginSuccessHandler(UsuarioService usuarioService, JwtService jwtService) {
		this.usuarioService = usuarioService;
		this.jwtService = jwtService;
	}

	@Value("${frontend.url}:http://localhost:5173")
	private String frontendUrl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		final Object principal = authentication.getPrincipal();
		if (!(principal instanceof OAuth2User oAuth2User))
			throw new IllegalStateException("Principal is not OAuth2User");
		final String email = oAuth2User.getAttribute("email");
		final String name = oAuth2User.getAttribute("name");
		final Optional<Usuario> existingUser = usuarioService.getUsuarioByEmail(email);

		if (existingUser.isPresent()) {
			final Usuario user = existingUser.get();
			final String token = jwtService.generateToken(user.getId(), user.getNombre(), user.getEmail(),
					user.getTipo());
			final String redirectUrl = frontendUrl + "/auth/callback?token="
					+ URLEncoder.encode(token, StandardCharsets.UTF_8);
			getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		} else {
			final String redirectUrl = frontendUrl + "/auth/register?email="
					+ URLEncoder.encode(email, StandardCharsets.UTF_8) +
					"&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
			getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		}
	}
}
