package com.vq.jwt.masoud.api.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.vq.jwt.masoud.api.utilities.JwtUtilities;

import io.jsonwebtoken.JwtException;

/**
 * This class is a handler for ActiveMQ "auth/masoud" topic messages
 */
public class ActiveMqAuthMasoudMsgHandler implements MessageHandler {

	@Autowired
	private JwtUtilities jwtUtilities;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	
	public void handleMessage(Message<?> message) throws MessagingException {
		if (message.getPayload() == null || !(message.getPayload() instanceof String)) {
			logger.error("Failed reason: invalid message payload");
			return;
		}
		String jwt = (String) message.getPayload();
		try {
			String error = jwtUtilities.validateJwt(jwt);
			if (error == null) {
				logger.debug("Success " + jwtUtilities.manipulateJwtForLogging(jwt));
			} else {
				logger.error(error);
			}
		} catch (JwtException e) {
			logger.error("Failed " + jwtUtilities.manipulateJwtForLogging(jwt) + " reason: " + e.getClass().getName());
		}
	}
}
