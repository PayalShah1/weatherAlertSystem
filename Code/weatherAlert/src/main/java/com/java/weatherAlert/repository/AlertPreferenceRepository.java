package com.java.weatherAlert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.weatherAlert.model.AlertPreference;

@Repository
public interface AlertPreferenceRepository extends JpaRepository<AlertPreference, Integer>{
	
	

}
