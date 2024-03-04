package com.abc.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Nipun
 */

public class PassEncrypt {

    private String saltvalue;
    private String hash;
            
    
    public static Map<String, String> getEncryptedPass(String password){
        String salt = PassBasedEnc.getSaltValue(30);
        Map<String, String> pass = new HashMap<>();
        pass.put("salt", salt);
        pass.put("hash", PassBasedEnc.generateSecurePassword(password, salt));
        return pass;
    }
    
    /* Driver Code */
    public String getHash(String password) {
        
        /* generates the Salt value. It can be stored in a database. */
        if(saltvalue == null){
            saltvalue = PassBasedEnc.getSaltValue(30);
        }
        
        /* generates an encrypted password. It can be stored in a database.*/
        hash = PassBasedEnc.generateSecurePassword(password, saltvalue);
        return hash;
    }

    public String getSalt() {
        /* generates the Salt value. It can be stored in a database. */
        if(saltvalue == null){
            saltvalue = PassBasedEnc.getSaltValue(30);
        }
        return saltvalue;
    }

    public static boolean isMatched(String password, String hashword, String saltvalue) {
        /* verify the original password and encrypted password */
        Boolean status = PassBasedEnc.verifyUserPassword(password, hashword, saltvalue);
        return status;
    }

}

class PassBasedEnc {

    /* Declaration of variables */
    private static final Random random = new SecureRandom();
    private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int iterations = 10000;
    private static final int keylength = 256;

    /* Method to generate the salt value. */
    public static String getSaltValue(int length) {
        StringBuilder finalval = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            finalval.append(characters.charAt(random.nextInt(characters.length())));
        }

        return new String(finalval);
    }

    /* Method to generate the hash value */
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keylength);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /* Method to encrypt the password using the original password and salt value. */
    public static String generateSecurePassword(String password, String salt) {
        String finalval;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        finalval = Base64.getEncoder().encodeToString(securePassword);

        return finalval;
    }

    /* Method to verify if both password matches or not */
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean finalval;

        /* Generate New secure password with the same salt */
        String newSecurePassword = generateSecurePassword(providedPassword, salt);

        /* Check if two passwords are equal */
        finalval = newSecurePassword.equalsIgnoreCase(securedPassword);

        return finalval;
    }
}
