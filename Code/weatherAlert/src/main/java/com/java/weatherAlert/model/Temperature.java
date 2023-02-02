package com.java.weatherAlert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Temperature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;
	
	@Column(name="temp_celcius")
	private float tempCelcius;
	
	@Column(name="temp_fahrenheit")
	private float tempFahrenheit;
	
	//@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "location_id", referencedColumnName="id")
	private Location location;

}
