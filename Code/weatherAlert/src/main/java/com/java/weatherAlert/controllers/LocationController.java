package com.java.weatherAlert.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.model.Location;
import com.java.weatherAlert.services.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	@RequestMapping(method = RequestMethod.GET,value="/getAll", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Location>> getAllLocations() throws NoDataFoundException {
		List<Location> locationList = locationService.getAllLocations();
		ResponseEntity<List<Location>> response  = new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		return response;
	}
}
