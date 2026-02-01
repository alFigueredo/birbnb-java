package com.panki.birbnb_backend.controller;

// import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panki.birbnb_backend.dto.TokenResponse;
import com.panki.birbnb_backend.dto.UsuarioRequest;
import com.panki.birbnb_backend.dto.UsuarioResponse;
import com.panki.birbnb_backend.service.JwtService;
import com.panki.birbnb_backend.service.UsuarioService;

@Controller
@RequestMapping("/api/auth")
public class PerfilController {

	private final UsuarioService usuarioService;
	private final JwtService jwtService;

	public PerfilController(UsuarioService usuarioService, JwtService jwtService) {
		this.usuarioService = usuarioService;
		this.jwtService = jwtService;
	}

	@GetMapping("/perfil")
	public Map<String, Object> perfil(@AuthenticationPrincipal OAuth2User user) {
		// return Collections.singletonMap("name", user.getAttribute("name"));
		return user.getAttributes();
	}

	@PostMapping("/register")
	public TokenResponse register(@RequestBody UsuarioRequest usuarioRequest) {
		final UsuarioResponse usuarioResponse = usuarioService.upsertUsuario(usuarioRequest);
		final String token = jwtService.generateToken(usuarioResponse.getId(), usuarioResponse.getNombre(),
				usuarioResponse.getEmail(), usuarioResponse.getTipoUsuario());
		return new TokenResponse(token);
	}
}
