package com.java.weatherAlert.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Table(name="alertpreference")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertPreference {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "Enter Start Time.")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "Enter End Time.")
	private Date endTime; 
	
	@Column(name="lower_threshold")
	@Digits(message="Temperature must be digit",fraction = 3, integer = 3)
	private float lowerThreshold;
	
	@Column(name="higher_threshold")
	@Digits(message="Temperature must be digit",fraction = 3, integer = 3)
	private float higherThreshold;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User_id", referencedColumnName="id")
	@NotNull
	private User user;
	
	@OneToOne
	@JoinColumn(name = "location_id", referencedColumnName="id")
	private Location location;

	@OneToOne
	@JoinColumn(name = "NotificationType_id", referencedColumnName="id")
	private NotificationType notificationType;
	
	@Column(name="isActive",columnDefinition = "boolean default true")
	private boolean active;
}
