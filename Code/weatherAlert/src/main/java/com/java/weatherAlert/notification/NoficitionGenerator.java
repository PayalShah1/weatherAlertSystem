package com.java.weatherAlert.notification;

import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Temperature;

public interface NoficitionGenerator {
	
	public void sendNotification(AlertPreference alert, Temperature temperature);
	
	public String generateNotificationMessage(AlertPreference alert, Temperature temperature);
}
