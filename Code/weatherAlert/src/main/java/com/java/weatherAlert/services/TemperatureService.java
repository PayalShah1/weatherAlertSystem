package com.java.weatherAlert.services;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.weatherAlert.model.Temperature;
import com.java.weatherAlert.repository.TemperatureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TemperatureService {
	
	private static final Logger log = LoggerFactory.getLogger(TemperatureService.class);
	
	@Autowired
	TemperatureRepository temperatureRepository;
	
	@Cacheable("TemperatureCache")
	public Temperature createTemperature(Temperature temperature) {
		log.info("Create Tempearture Data Initiated");
		Optional optional = Optional.of(temperature);
		if(optional.isPresent()) {
			temperatureRepository.save(temperature);
		}
		return temperature;
	}
	
	@Cacheable("TemperatureCache")
	public Temperature getTemperatureByDateTime(Date date) {
		log.info("Get Temperature Details Initiated");
		Temperature temperature = temperatureRepository.findByDateTime(date);
		Optional optional = Optional.of(temperature);
		if(optional.isPresent()) {
			return temperature;
		}
		return null;
	}
	
	public Temperature getTemperatureById(int id) {
		log.info("Get Temperature Details Initiated");
		Temperature temperature = temperatureRepository.getById(id);
		Optional optional = Optional.of(temperature);
		if(optional.isPresent()) {
			return temperature;
		}
		return null;
	}

}
