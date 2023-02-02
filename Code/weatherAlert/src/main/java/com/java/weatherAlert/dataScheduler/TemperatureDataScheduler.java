package com.java.weatherAlert.dataScheduler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.alertGenerator.AlertGenerator;
import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Location;
import com.java.weatherAlert.model.Temperature;
import com.java.weatherAlert.model.User;
import com.java.weatherAlert.notification.EmailNotificationGenerator;
import com.java.weatherAlert.repository.LocationRepository;
import com.java.weatherAlert.services.AlertPreferenceService;
import com.java.weatherAlert.services.LocationService;
import com.java.weatherAlert.services.TemperatureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TemperatureDataScheduler implements WeatherDataScheduler{
	
	private static final Logger log = LoggerFactory.getLogger(TemperatureDataScheduler.class);

	@Autowired
	LocationService locationService;

	@Autowired
	TemperatureService tempService;

	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	AlertPreferenceService alertPreferenceService;
	
	@Autowired
	AlertGenerator alertGenerator;
	
	@Autowired
	CacheManager cachemanager;
	
	private static String requestUrl = "https://weatherapi-com.p.rapidapi.com/current.json?q=";

	/*
	 * This method fetches details of weather from third party api every 10 mins and push it for voilation check
	 */
	@Scheduled(fixedRate = 60000 * 10)
	public void getData() {
		//here only one api was found to be used for free, so using random number gererator for second api
		log.info("Cron job started to get temperature data");

		//get all locations and send request for them and store the result in database
		List<Location> locationList;
		try {
			locationList = locationService.getAllLocations();
		} catch (NoDataFoundException e1) {
			log.error("No locations added..Admin add locations");
			return;
		}

		locationList.forEach(location -> {
			//System.out.println(location);
			String cityName = location.getCityName();
			String requestUrlforLocation = requestUrl + cityName;
			System.out.println(requestUrlforLocation);

			HttpRequest request;
			try {
				log.info("Sending request to get Real-time tempearure data from weatherapi:");
				log.info("Location :"  + cityName);
				log.info("Request :"  + requestUrlforLocation);
				request = HttpRequest.newBuilder()
						.uri(new URI(requestUrlforLocation))
						.header("X-RapidAPI-Key", "5a371b2d19msh2a807c8e6520d76p164369jsnfc0e532f5cc4")
						.header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
						.method("GET", HttpRequest.BodyPublishers.noBody())
						.build();
				log.info("For Location :"  + cityName);
				log.info("Request :"  + requestUrlforLocation);;
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				
				Thread.sleep(5000);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode tempData1 = mapper.readTree(response.body());

				log.info("Temperature point received" + tempData1.toString());
				
				//get temperature data from second api : here second api had subscription charges, so using local temperature generator
				float tempData2_celcius = this.localTempratureGenerator();
				float tempData2_f = this.localTempratureGenerator();
				
				float finalTemperature_celcius = this.findMeanTemperature(tempData1.get("current").get("temp_c").floatValue(), tempData2_celcius); 
				float finalTemperature_f = this.findMeanTemperature(tempData1.get("current").get("temp_f").floatValue(), tempData2_f); 
				
				//create temperature 
				Temperature tempPoint = new Temperature();

				tempPoint.setLocation(location);

				tempPoint.setTempCelcius(finalTemperature_celcius);
				tempPoint.setTempFahrenheit(finalTemperature_f);
				//convert epoch string to date and fix this
				String dateEpoch = tempData1.get("current").get("last_updated_epoch").asText();
				long fbt = Long.parseLong(dateEpoch);
				Date date = new Date(fbt);
				System.out.println(date);
				tempPoint.setDateTime(date);
				
				//store temperature
				tempService.createTemperature(tempPoint);
				
				//check for violation
				alertGenerator.evaluateViolation(tempPoint);
				
			} catch (URISyntaxException | IOException | InterruptedException e) {
				log.error("Get Real-time Temperature Failed !!!\n" + e.getStackTrace().toString());
			}

		});
		
	}
	
	
	public float localTempratureGenerator() {
		
		Random rand = new Random();
		  
        float temp_celcius = rand.nextFloat(50);
        return temp_celcius;
	}
	
	public float findMeanTemperature(float temp1, float temp2) {
		float finalTemp = (temp1 + temp2)/2;
		return finalTemp;
	}
}
 