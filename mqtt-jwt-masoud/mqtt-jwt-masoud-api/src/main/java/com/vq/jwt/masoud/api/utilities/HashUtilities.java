package com.vq.jwt.masoud.api.utilities;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

/**
 * This is a Spring component which provides hashing utilities
 */
@Component
public class HashUtilities {

	
	public String md5Txt(String txtToHash) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (java.security.NoSuchAlgorithmException e) {
			return null;
		}
		byte[] digestedBytesArr = messageDigest.digest(txtToHash.getBytes());
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < digestedBytesArr.length; ++i) {
			stringBuffer.append(Integer.toHexString((digestedBytesArr[i] & 0xFF) | 0x100).substring(1,3));
		}
		return stringBuffer.toString();
    }
}
