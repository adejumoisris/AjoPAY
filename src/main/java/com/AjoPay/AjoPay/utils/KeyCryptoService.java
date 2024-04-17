package com.AjoPay.AjoPay.utils;

import org.apache.commons.codec.digest.Sha2Crypt;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.SecureRandom;

@Component
public class KeyCryptoService implements Serializable {
    private static final String ALGORITHM = "AEC/CTR/NoPadding";
    private static final int BITS128 = 16;
    private static final int BITS256 = 32;
    private static final SecureRandom random = new SecureRandom();


    public String generateActivationCode(int optLength){
        return RandomStringUtils.randomNumeric(optLength);
    }
    // salting String
    public String hash(String source, String salt){
        return null;
    }

}
