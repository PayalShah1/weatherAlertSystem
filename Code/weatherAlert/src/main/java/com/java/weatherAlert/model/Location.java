package com.java.weatherAlert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

	 @Column(name="cityName",unique=true) 
	 private String cityName;
	 
	 private String state;
	 
	 private String country;
	 
	 //@JsonBackReference
	 @OneToOne
	 private Temperature temperature;
	 
	// @JsonBackReference
	 @OneToOne
	 private AlertPreference alertPreference;
}
