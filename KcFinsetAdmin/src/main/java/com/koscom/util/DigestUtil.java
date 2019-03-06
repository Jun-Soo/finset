/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.koscom.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.xerces.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Digest Utility Class <br>
 * Based on string-specific character set or base64, provide functions such as
 * encode/decode function and string convert function by using digest algorithm
 * of MD5 or SHA.<br>
 *
 * org.anyframe.util 패키지 클래스를 목적에 맞게 수정하여 사용합니다.
 *
 * @author HyunJung Jeong
 *
 */
public final class DigestUtil {

	private DigestUtil() {
		throw new AssertionError();
	}

	/** The <code>Log</code> instance for this class. */
//	private static final Logger logger = LoggerFactory.getLogger(DigestUtil.class);



	/**
	 * Encode a string using Base64 encoding. This is weak encoding in that
	 * anyone can use the decodeString routine to reverse the encoding.
	 *
	 * @param str
	 *            String to be encoded
	 * @return String encoding result
	 * @see Base64#encode(byte[])
	 */
	public static String encodeBase64(String str) {
		return Base64.encode(str.getBytes());
	}

	/**
	 * Decode a string using Base64 encoding.
	 *
	 * @param str
	 *            String to be decoded
	 * @return String decoding String
	 * @see Base64#decode(String)
	 */
	public static String decodeBase64(String str) {
		return new String(Base64.decode(str));
	}

	/**
	 * Encode a string using algorithm specified in web.xml and return the
	 * resulting encrypted password. If exception, the plain credentials string
	 * is returned
	 *
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * @return encrypted password based on the algorithm.
	 * @throws NoSuchAlgorithmException
	 */
	public static String encodePassword(String password, String algorithm)
			throws NoSuchAlgorithmException {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		md = MessageDigest.getInstance(algorithm);

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuilder buf = new StringBuilder();

		for (int i = 0; i < encodedPassword.length; i++) {
			if (((int) encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString((int) encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

}
