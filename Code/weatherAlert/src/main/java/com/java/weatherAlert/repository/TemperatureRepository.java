package com.java.weatherAlert.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.weatherAlert.model.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer>{
	
	
	 Temperature findByDateTime(Date date);

}
