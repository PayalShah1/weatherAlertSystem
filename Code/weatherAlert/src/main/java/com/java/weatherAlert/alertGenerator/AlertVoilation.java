package com.java.weatherAlert.alertGenerator;

import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Temperature;

public class AlertVoilation {
	
	private AlertPreference alertPreferenc;
	private Temperature temperature;
	
	public AlertVoilation() {
	}
	
	public AlertVoilation(AlertPreference alertPreferenc, Temperature temperature) {
		this.alertPreferenc = alertPreferenc;
		this.temperature = temperature;
	}

	
}
