package com.panki.birbnb_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class HealthCheckController {

	@GetMapping(path = "/health")
	public @ResponseBody String healthcheck() {
		return "OK";
	}

}
