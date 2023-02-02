package com.java.weatherAlert.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.weatherAlert.Exception.CreateFailedException;
import com.java.weatherAlert.Exception.NoDataFoundException;
import com.java.weatherAlert.model.AlertPreference;
import com.java.weatherAlert.model.Location;
import com.java.weatherAlert.model.NotificationType;
import com.java.weatherAlert.services.AlertPreferenceService;
import com.java.weatherAlert.services.LocationService;
import com.java.weatherAlert.services.NotificationTypeService;

@Controller
public class MainController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	NotificationTypeService notificationTypeService;
	
	@Autowired
	AlertPreferenceService alertPreferenceService;
	
	
	@RequestMapping("/") 
	public String homePage(){
		return "home";
	}
	
	@RequestMapping("/showForm")
	public String showRegisterAlertForm(Model model) throws NoDataFoundException {
		System.out.println("This is Main page...");
		AlertPreference alert = new AlertPreference();
		
		model.addAttribute("alert", alert);
		
		List<Location> locationList = locationService.getAllLocations();
		model.addAttribute("locationList", locationList);
		
		List<NotificationType> notificationTypeList = notificationTypeService.getAllNotificationtypes();
		model.addAttribute("notificationTypeList", notificationTypeList);
		return "registerAlert";
	}
	
	@RequestMapping("/alertNotification")
	public String notification() {
		System.out.println("This is Notification page...");
		return "alertNotification";
	}
	
	@GetMapping("/register")
	public String showAlert(Model model, BindingResult result) throws NoDataFoundException {
		return "registerAlert_succes";
	}
	
	
	@PostMapping("/register") 
	public String registerAlert(@ModelAttribute("alert") @Valid AlertPreference alert, BindingResult result,Model model) throws NoDataFoundException, CreateFailedException, MethodArgumentNotValidException{
		//System.out.println(alert);
		if (result.hasErrors()) {
            return "registerAlert";
        }
		alertPreferenceService.createAlertPreference(alert);
		model.addAttribute("alert", alert);
		return "registerAlert_succes";
	}
}
