package com.panki.birbnb_backend.dto;

public class TokenResponse {

	final String token;

	public TokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
