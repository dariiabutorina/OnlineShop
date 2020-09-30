package com.internet.shop.util;

import com.internet.shop.exceptions.PasswordHashingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class HashUtil {
    private static final Logger LOGGER = Logger.getLogger(HashUtil.class);
    private static final String LOG4J_PROPERTIES =
            "/Users/dariiapikul/IdeaProjects/OnlineShop/src/main/resources/log4j.properties";
    private static final String HASHING_ALGORITHM = "SHA-512";
    private static final String MESSAGE =
            "Password hashing failed. Unable to find the chosen algorithm: " + HASHING_ALGORITHM;

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte element : digest) {
                hashedPassword.append(String.format("%02x", element));
            }
        } catch (NoSuchAlgorithmException exception) {
            PropertyConfigurator.configure(LOG4J_PROPERTIES);
            LOGGER.error(exception);
            throw new PasswordHashingException(MESSAGE, exception);
        }
        return hashedPassword.toString();
    }
}

