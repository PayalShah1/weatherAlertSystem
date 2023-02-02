package com.java.weatherAlert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.weatherAlert.Exception.CreateFailedException;
import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Location;
import com.java.weatherAlert.model.NotificationType;
import com.java.weatherAlert.model.Temperature;
import com.java.weatherAlert.model.User;
import com.java.weatherAlert.repository.AlertPreferenceRepository;
import com.java.weatherAlert.services.AlertPreferenceService;
import com.java.weatherAlert.services.LocationService;
import com.java.weatherAlert.services.NotificationTypeService;
import com.java.weatherAlert.services.TemperatureService;



@SpringBootTest
class WeatherAlertApplicationTests {
	
	@Autowired
	private AlertPreferenceRepository alertPreferenceRepository;
	
	@Autowired
	private AlertPreferenceService alertPreferenceService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private NotificationTypeService notificationTypeService;
	
	@Autowired
	private TemperatureService temperatureService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAllLocationsApiTest() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet( "http://localhost:8080/location/getAll" );
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

	    assertEquals(httpResponse.getStatusLine().getStatusCode(),(HttpStatus.OK));
	}

	
	@Test
	public void getAllNotificationTypeApiTest() throws ClientProtocolException, IOException {

	    HttpUriRequest request = new HttpGet( "http://localhost:8080/notificationType/getAll" );
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

	    assertEquals(httpResponse.getStatusLine().getStatusCode(),(HttpStatus.OK));
	    assertNotNull(httpResponse.getEntity());
	}
	
	@Test
	public void createAlertPreferenceApiTest() throws NoDataFoundException {
		
		Location location = locationService.getLocationById(1);
		NotificationType notificationType = notificationTypeService.getAllNotificationtypes().get(1);
		
		User user = new User();
		user.setUserName("userTest");
		user.setEmail("userTest1@gmail.com");
		user.setContactNumber("9988223355");
		
		AlertPreference alertPreference = new AlertPreference();
		alertPreference.setUser(user);
		alertPreference.setStartTime(new Date());
		alertPreference.setEndTime(new Date(0,3,30));
		alertPreference.setLowerThreshold((float) 10.5);
		alertPreference.setHigherThreshold((float)30);
		alertPreference.setLocation(location);
		alertPreference.setNotificationType(notificationType);
		alertPreference.setActive(true);
		
		
		
		 ObjectMapper Obj = new ObjectMapper();	
		 try {
	            String alertEnity = Obj.writeValueAsString(alertPreference);
	            
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost postRequest = new HttpPost("http://localhost:8080/alert/create");
	             
	            postRequest.addHeader("content-type", "application/json");

	            StringEntity userEntity = new StringEntity(alertPreference.toString());
	            postRequest.setEntity(userEntity);
	              
	            HttpResponse response = httpClient.execute(postRequest);
	            
	            assertEquals(response.getStatusLine().getStatusCode(),(HttpStatus.OK));
	    	    assertNotNull(response.getEntity());
	        } catch (IOException e) {
	 
	        }
	    }
	
	
	@Test
	public void createAlertServiceTest() throws NoDataFoundException, IllegalArgumentException, CreateFailedException {
		Location location = locationService.getLocationById(1);
		NotificationType notificationType = notificationTypeService.getAllNotificationtypes().get(1);
		
		User user = new User();
		user.setUserName("userTest");
		user.setEmail("userTest1@gmail.com");
		user.setContactNumber("9988223355");
		
		AlertPreference alertPreference = new AlertPreference();
		alertPreference.setUser(user);
		alertPreference.setStartTime(new Date());
		alertPreference.setEndTime(new Date(0,3,30));
		alertPreference.setLowerThreshold((float) 10.5);
		alertPreference.setHigherThreshold((float)30);
		alertPreference.setLocation(location);
		alertPreference.setNotificationType(notificationType);
		alertPreference.setActive(true);
		
		AlertPreference addedAlert = alertPreferenceService.createAlertPreference(alertPreference);
		
		assertNotNull(alertPreferenceService.getAlertById(addedAlert.getId()));
	}
	
	@Test
	public void getAllLocationServiceTest() throws NoDataFoundException {

	       List<Location> locations = locationService.getAllLocations();

	       assertThat(locations).isNotNull();
	       assertThat(locations.size()).isEqualTo(11);
	}
	
	@Test
	public void getAllNotificationTypeServiceTest() throws NoDataFoundException {
	       List<NotificationType> allNotificationTypes = notificationTypeService.getAllNotificationtypes();
	       assertThat(allNotificationTypes).isNotNull();
	       assertThat(allNotificationTypes.size()).isEqualTo(4);
	}
	
	//create temperature test 
		@Test
		public void createTemperatureServiceTest() throws NoDataFoundException {
			Location testLocation = locationService.getLocationById(1);
			
			Temperature testTemperature = new Temperature();
			testTemperature.setLocation(testLocation);
			testTemperature.setDateTime(new Date());
			testTemperature.setTempCelcius((float)32.1);
			testTemperature.setTempFahrenheit((float)89.78);
			
			Temperature addedTemperature = temperatureService.createTemperature(testTemperature);
			
			assertNotNull(temperatureService.getTemperatureById(addedTemperature.getId()));
		}
}
