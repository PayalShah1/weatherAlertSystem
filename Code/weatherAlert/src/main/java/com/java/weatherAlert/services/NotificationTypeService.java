package com.java.weatherAlert.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.model.NotificationType;
import com.java.weatherAlert.repository.NotificationTypeRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class NotificationTypeService {
	
	private static final Logger log = LoggerFactory.getLogger(NotificationTypeService.class);
	
	@Autowired
	NotificationTypeRepository notificationTypeRepository;
	
	public List<NotificationType> getAllNotificationtypes() throws NoDataFoundException {
		log.info("Get All Notification Types Initiated");
		List<NotificationType> notificationTypeList = notificationTypeRepository.findAll();
		if(notificationTypeList.isEmpty()) {
			log.error("No Data Found for Notification Type!!!");
			throw new NoDataFoundException();
		}
		return notificationTypeList;
	} 

}
