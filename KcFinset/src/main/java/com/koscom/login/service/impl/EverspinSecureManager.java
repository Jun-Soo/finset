package com.koscom.login.service.impl;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.koscom.login.service.SecureManager;

import kr.co.everspin.eversafe.keypad.SecureTextDecoder;
public class EverspinSecureManager implements SecureManager {

    private static final Logger logger = LoggerFactory.getLogger(EverspinSecureManager.class);

    @Value("${everspin.rsa.public.key}")
    private String mStrPublicKey;

    @Value("${everspin.rsa.private.key}")
    private String mStrPrivateKey;

    @Override
    public String getDecodedPassword(String password, Object... objects) {

        logger.info("1비밀번호 복호화 시작: {}");
        logger.debug("encoded password: {}", password);
        //logger.debug("private key: {}", mStrPrivateKey);

        if ( StringUtils.isEmpty( password ) ) {
            logger.info("요청 비밀번호 누락");
            return password;
        }

        String result = password;

        SecureTextDecoder passwordDecoder = SecureTextDecoder.RSADecoder(mStrPrivateKey);
        logger.debug("passwordDecoder is null? {}", String.valueOf(passwordDecoder == null));
        try {
            result = passwordDecoder.originalTextForSecureText(result);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        //logger.debug("decoded password: {}", result);

        return result;
    }
}
