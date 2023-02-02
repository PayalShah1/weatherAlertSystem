package com.java.weatherAlert.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.model.NotificationType;
import com.java.weatherAlert.services.NotificationTypeService;

@RestController
@RequestMapping("/notificationType")
public class NotificationTypeController {

	@Autowired
	NotificationTypeService notificationTypeService;
	
	@RequestMapping(method = RequestMethod.GET,value="/getAll", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NotificationType>> getAllLocations() throws NoDataFoundException {
		List<NotificationType> notificationTypeList = notificationTypeService.getAllNotificationtypes();
		ResponseEntity<List<NotificationType>> response  = new ResponseEntity<List<NotificationType>>(notificationTypeList, HttpStatus.OK);
		return response;
	}

}
