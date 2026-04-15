package com.noobchain;

import java.security.MessageDigest;

// Utility class providing cryptographic helper methods for the blockchain.
public class StringUtil {

    /**
     * Applies the SHA-256 hashing algorithm to a given string input.
     *
     * @param input the string to hash
     * @return the resulting SHA-256 hash as a hex string
     */
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the input string as bytes
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            // Convert the raw bytes into a readable hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to apply SHA-256", e);
        }
    }
}
