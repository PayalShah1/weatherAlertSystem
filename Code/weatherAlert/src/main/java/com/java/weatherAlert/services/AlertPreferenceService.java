package com.java.weatherAlert.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.java.weatherAlert.Exception.CreateFailedException;
import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.repository.AlertPreferenceRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AlertPreferenceService {
	
	private static final Logger log = LoggerFactory.getLogger(AlertPreferenceService.class);
	
	@Autowired
	AlertPreferenceRepository alertPreferenceRepository;
	
	@Cacheable("AlertPreferenceCache")
	public AlertPreference createAlertPreference(AlertPreference alertPreference) throws CreateFailedException,IllegalArgumentException {
		log.info("Create AlertPreference Initiated");
		Optional optional = Optional.of(alertPreference);
		if(optional.isPresent()) {
			try {
				alertPreferenceRepository.save(alertPreference);
			}catch (DataIntegrityViolationException  e) {
				log.error("Alert creation failed!!!\n" + e.getMessage());
				throw new CreateFailedException(e.getMessage());
			} catch (IllegalArgumentException e) {
				log.error("Alert creation failed!!!Illegal Arguments\n" + e.getMessage());
				throw new IllegalArgumentException();
			} catch (Exception e) {
				throw new CreateFailedException(e.getMessage());
			}
			
		}	
		System.out.println(alertPreference);
		log.info("Create AlertPreference Successfull");
		return alertPreference;
	}

	@Cacheable("AlertPreferenceCache")
	public List<AlertPreference> getAlertPreferenceWithinTimeRange(Date startDate, Date endDate) {
		log.info("Get Alert Within Range Initiated");
		List <AlertPreference> allAlertPrefs = alertPreferenceRepository.findAll();
		//filter here 
		Optional optional = Optional.of(allAlertPrefs);
		if(optional.isPresent()) {
			List <AlertPreference> alertPrefs = allAlertPrefs.stream()
													.filter(a -> a.getStartTime().before(startDate) 
																&& a.getEndTime().after(endDate))
													.collect(Collectors.toList());
			return alertPrefs;
		}
		
		return null;
	}
	
	public AlertPreference getAlertById(int id) {
		AlertPreference alert = alertPreferenceRepository.getById(id);
		return getAlertById(id);
	}
}
