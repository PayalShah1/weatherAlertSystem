package com.java.weatherAlert.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.weatherAlert.Exception.CreateFailedException;
import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.services.AlertPreferenceService;

@RestController
@RequestMapping("/alert")
public class AlertController {
	@Autowired
	AlertPreferenceService alertPreferenceService;
	
	@RequestMapping(method = RequestMethod.POST, value="/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlertPreference> registerAlert(@RequestBody @Valid AlertPreference alertPreference) throws CreateFailedException,MethodArgumentNotValidException {
		System.out.println("Register new alert:" + alertPreference.toString());
		AlertPreference createdAlert;
			createdAlert = alertPreferenceService.createAlertPreference(alertPreference);
			ResponseEntity<AlertPreference> response = new ResponseEntity<AlertPreference>(createdAlert, HttpStatus.OK);
			return 	response;
	}	
	
}
