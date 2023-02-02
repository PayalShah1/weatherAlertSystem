package com.java.weatherAlert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@NotEmpty(message = "Enter Username.")
	private String userName;
	
	@NotEmpty(message = "Enter Email.")
	@Email(message="Enter valid Email Id.")
	private String email;
	
	@Column(name="contact_number")
	@NotEmpty(message = "Enter Contact number.")
	@Pattern(regexp = "^\\d{10}$")
	private String contactNumber;
	
	//@JsonBackReference
	@OneToOne
	private AlertPreference alertPreference;

}
