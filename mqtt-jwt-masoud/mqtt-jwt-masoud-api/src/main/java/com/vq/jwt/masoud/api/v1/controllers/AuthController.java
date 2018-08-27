package com.vq.jwt.masoud.api.v1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vq.jwt.masoud.api.activemq.ActivemqMqttConfigs.AuthMasoudMsgGateway;
import com.vq.jwt.masoud.api.models.User;
import com.vq.jwt.masoud.api.repositories.UserRepo;
import com.vq.jwt.masoud.api.utilities.JwtUtilities;
import com.vq.jwt.masoud.api.utilities.HashUtilities;
import com.vq.jwt.masoud.api.v1.rest_models.inputs.AuthReq;
import com.vq.jwt.masoud.api.v1.rest_models.outputs.AuthRes;


/**
 * This is the v1 version of AuthController which is responsible for authenticating users and generating JWTs for them
 */
@RestController("authControllerV1")
@RequestMapping("/api/v1/")
public class AuthController {
		
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthMasoudMsgGateway authMasoudMsgGateway;
	
	@Autowired
	private JwtUtilities jwtUtilities;
	
	@Autowired
	private HashUtilities hashUtilities;
	
	
	
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public ResponseEntity<?> newAuthReq(@RequestBody @Valid AuthReq input) {
		
		User user = userRepo.findByEmail(input.email);
		if(user == null) {
			return new ResponseEntity<>(new AuthRes("Unauthorized"), HttpStatus.UNAUTHORIZED);
		}
		
		String hashOfInputPass = hashUtilities.md5Txt(input.password);
		if(hashOfInputPass == null || !hashOfInputPass.toLowerCase().equals(user.getPassword())) {
			return new ResponseEntity<>(new AuthRes("Unauthorized"), HttpStatus.UNAUTHORIZED);
		}
		
		authMasoudMsgGateway.sendToMqtt(jwtUtilities.generateJwt(input.email));
		return new ResponseEntity<>(new AuthRes("Authorized"), HttpStatus.OK);
	}
}
