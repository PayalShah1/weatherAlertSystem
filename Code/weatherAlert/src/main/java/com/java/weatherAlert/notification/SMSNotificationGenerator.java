package com.java.weatherAlert.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Temperature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SMSNotificationGenerator implements NoficitionGenerator {
	
	private static final Logger log = LoggerFactory.getLogger(SMSNotificationGenerator.class);

	public void sendNotification(AlertPreference alert, Temperature temperature) {
		log.info("SMS Notification Initiated");
		String alertMessage  = this.generateNotificationMessage(alert, temperature);
		System.out.println("Sending SMS notification");
		System.out.println("Receiver:"+alert.getUser().getContactNumber());
		System.out.println("Subject:"+ "Temperature threshold voilation");
		System.out.println("Message:" + alertMessage);
	}
	
	public String generateNotificationMessage(AlertPreference alert, Temperature temperature) {
		String message = "Hello " + alert.getUser().getUserName() + ",\n\tTemperature threshold voilation found\n";
		message = message + "For location:" +alert.getLocation().getCityName() + ", at:" + temperature.getDateTime() + "\tCurrent temperature :" + temperature.getTempCelcius() + "C" ;
		return message;
	}
}
