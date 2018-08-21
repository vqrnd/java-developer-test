package com.vq.jwt.masoud.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
/**
 * This class is responsible for reading project specific configs & properties from global.properties file
 * and provide these configs to other components
 */
public class CustomConfigs {
	
	@Value("${mqtt-jwt-masoud-api.jwt.secret}")
	private String jwtSecret;
	
	@Value("${mqtt-jwt-masoud-api.jwt.subject}")
	private String jwtSubject;
	
	@Value("${mqtt-jwt-masoud-api.jwt.duration-in-minutes}")
	private int jwtDurationInMinutes;
	
	@Value("${mqtt-jwt-masoud-api.activemq.url}")
	private String activeMqUrl;
	
	@Value("${mqtt-jwt-masoud-api.activemq.input_client_id}")
	private String activeMqInputClientId;
	
	@Value("${mqtt-jwt-masoud-api.activemq.output_client_id}")
	private String activeMqOutputClientId;
	
	@Value("${mqtt-jwt-masoud-api.activemq.user}")
	private String activeMqUser;
	
	@Value("${mqtt-jwt-masoud-api.activemq.pass}")
	private String activeMqPass;
	
	@Value("${mqtt-jwt-masoud-api.activemq.topic}")
	private String activeMqTopic;
	
	@Value("${mqtt-jwt-masoud-api.activemq.qos}")
	private int activeMqQos;

	
	
	
	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
	
	public String getJwtSubject() {
		return jwtSubject;
	}

	public void setJwtSubject(String jwtSubject) {
		this.jwtSubject = jwtSubject;
	}

	public int getJwtDurationInMinutes() {
		return jwtDurationInMinutes;
	}

	public void setJwtDurationInMinutes(int jwtDurationInMinutes) {
		this.jwtDurationInMinutes = jwtDurationInMinutes;
	}

	public String getActiveMqUrl() {
		return activeMqUrl;
	}

	public void setActiveMqUrl(String activeMqUrl) {
		this.activeMqUrl = activeMqUrl;
	}

	public String getActiveMqInputClientId() {
		return activeMqInputClientId;
	}

	public void setActiveMqInputClientId(String activeMqInputClientId) {
		this.activeMqInputClientId = activeMqInputClientId;
	}

	public String getActiveMqOutputClientId() {
		return activeMqOutputClientId;
	}

	public void setActiveMqOutputClientId(String activeMqOutputClientId) {
		this.activeMqOutputClientId = activeMqOutputClientId;
	}

	public String getActiveMqUser() {
		return activeMqUser;
	}

	public void setActiveMqUser(String activeMqUser) {
		this.activeMqUser = activeMqUser;
	}

	public String getActiveMqPass() {
		return activeMqPass;
	}

	public void setActiveMqPass(String activeMqPass) {
		this.activeMqPass = activeMqPass;
	}

	public String getActiveMqTopic() {
		return activeMqTopic;
	}

	public void setActiveMqTopic(String activeMqTopic) {
		this.activeMqTopic = activeMqTopic;
	}

	public int getActiveMqQos() {
		return activeMqQos;
	}

	public void setActiveMqQos(int activeMqQos) {
		this.activeMqQos = activeMqQos;
	}
}
