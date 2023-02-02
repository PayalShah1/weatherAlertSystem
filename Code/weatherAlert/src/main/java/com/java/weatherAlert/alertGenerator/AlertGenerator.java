package com.java.weatherAlert.alertGenerator;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Temperature;
import com.java.weatherAlert.notification.ApplicationNotificationGenerator;
import com.java.weatherAlert.notification.EmailNotificationGenerator;
import com.java.weatherAlert.notification.SMSNotificationGenerator;
import com.java.weatherAlert.services.AlertPreferenceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlertGenerator {
	
	private static final Logger log = LoggerFactory.getLogger(AlertGenerator.class);
	
	@Autowired
	AlertPreferenceService alertPreferenceService;
	
	@Autowired
	EmailNotificationGenerator emailGenerator;
	
	@Autowired
	SMSNotificationGenerator smsGenerator;
	
	@Autowired
	ApplicationNotificationGenerator appNotificationGenerator;
	
	//this method evaluate for temperature alert violations
	public void evaluateViolation(Temperature temperature) {
		
		log.info("Alert violatio evaluation Initiated");
		
		Date currentDate = new Date();
		List<AlertPreference> alertPreferenceList = alertPreferenceService.getAlertPreferenceWithinTimeRange(currentDate, currentDate);
		alertPreferenceList.forEach(alertPreference -> {
			
			if((temperature.getLocation().getCityName().equalsIgnoreCase(alertPreference.getLocation().getCityName())) &&(temperature.getTempCelcius() > alertPreference.getHigherThreshold() || temperature.getTempCelcius() < alertPreference.getLowerThreshold())) {
				System.out.println("\n\n");
				log.info("Alert violatio found:\n Temperature:" + temperature.getTempCelcius() + ", Location:" + temperature.getLocation().getCityName());
				
				AlertVoilation alertVoilation = new AlertVoilation(alertPreference,temperature);
				
				if(alertPreference.getNotificationType().getType().equalsIgnoreCase("email")) {
					log.info("Email Notification Initiated");
					emailGenerator.sendNotification(alertPreference, temperature);
				} 
				if(alertPreference.getNotificationType().getType().equalsIgnoreCase("sms")) {
					log.info("SMS Notification Initiated");
					appNotificationGenerator.sendNotification(alertPreference, temperature);
				}
				if(alertPreference.getNotificationType().getType().equalsIgnoreCase("app")) {
					log.info("App Notification Initiated");
					smsGenerator.sendNotification(alertPreference, temperature);
				}
				if(alertPreference.getNotificationType().getType().equalsIgnoreCase("all")) {
					log.info("All Notifications Initiated");
					emailGenerator.sendNotification(alertPreference, temperature);
					appNotificationGenerator.sendNotification(alertPreference, temperature);
					smsGenerator.sendNotification(alertPreference, temperature);
				}
				
				System.out.println("\n\n");
			}
		});
	}

}
