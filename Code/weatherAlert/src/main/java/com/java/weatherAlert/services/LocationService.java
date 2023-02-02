package com.java.weatherAlert.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.model.Location;
import com.java.weatherAlert.repository.LocationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationService {
	
	private static final Logger log = LoggerFactory.getLogger(LocationService.class);
	
	@Autowired
	LocationRepository locationRepository;
	
	public List<Location> getAllLocations() throws NoDataFoundException {
		log.info("Get All Locations Initiated");
		List<Location> allLocationList = locationRepository.findAll();
		if(allLocationList.isEmpty()) {
			log.error("Location Data Not Found");
			throw new NoDataFoundException();
		}
		return allLocationList;
	}
	
	public Location getLocationById(int id) throws NoDataFoundException {
		Location location = locationRepository.getById(id);
		Optional optional = Optional.ofNullable(location);
		if(optional.isEmpty()) {
			log.error("Location Data Not Found");
			throw new NoDataFoundException();
		}
		return location;
	}

}
