package com.vq.jwt.masoud.api.v1.rest_models.inputs;

import javax.validation.constraints.*;

public class AuthReq {

	@NotEmpty
	@Email
	public String email;
	
	@NotEmpty
	public String password;
}
