package com.panki.birbnb_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.panki.birbnb_backend.security.JwtAuthenticationFilter;
import com.panki.birbnb_backend.security.OAuth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler,
			JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/oauth2/**", "/login/**").permitAll()
						.requestMatchers("/api/**").permitAll()
						.anyRequest().authenticated())
				.oauth2Login(oauth -> oauth.successHandler(oAuth2LoginSuccessHandler))
				.logout(logout -> logout.logoutSuccessUrl("/"))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
