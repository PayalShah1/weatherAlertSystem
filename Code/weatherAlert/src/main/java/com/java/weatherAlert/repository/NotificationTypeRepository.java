package com.java.weatherAlert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.weatherAlert.model.NotificationType;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer>{

}
