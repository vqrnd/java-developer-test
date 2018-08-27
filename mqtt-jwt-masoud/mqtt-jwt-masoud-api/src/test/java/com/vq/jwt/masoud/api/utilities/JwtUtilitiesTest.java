package com.vq.jwt.masoud.api.utilities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vq.jwt.masoud.api.CustomConfigs;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilitiesTest {

	@Mock
	CustomConfigs customConfigs;

	@InjectMocks
	JwtUtilities jwtUtilities;

	@Test
	public void testManipulateJwtForLogging() {
		String jwt = "header1122.PAYLOAD9988.55signiture66Z";
		assertEquals(
				"#**#*#-1123.#*##**#7989.53#*##*#*#*67#", 
				jwtUtilities.manipulateJwtForLogging(jwt));
		
		jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdXRoL21hc291ZCIsImV4cCI6MTUzNDgzOTIyNCwiZW1haWwiOiJ1c2VyNEB0ZXN0LmxvY2FsIiwiYXV0aG9yaXplZCI6dHJ1ZX0.1EiwrEgoPAV1ObWR0oXgQ0arV1IqU4GZHdEgSqhE-nU";
		assertEquals(
				"*######***#**#*1#*#9.*#####****#####*#21##29-1##*#*##4##*6##*#####*#*####*##1#*##***#-1#2###*#0###0#####2##**#*###0*#9#*#####*6###-1##1.1**##*#*#*#-1*###1*###0*##-1*#*4####*####*-#*",
				jwtUtilities.manipulateJwtForLogging(jwt));
	}

}
