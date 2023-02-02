package com.java.weatherAlert.Exception;

public class CreateFailedException extends Exception{
	
	public CreateFailedException(String message) {
		super("Failed to create:" + message);
	}
}
