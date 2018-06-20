package com.example.isinyavskiy.lab5.utils;

import java.security.MessageDigest;

public class CredentialsUtils {

    public static String getHash(String ts, String private_key, String public_key){
        String hash = ts+private_key+public_key;
        try {
            return genHash(hash);
        } catch (Exception e) {
            return "";
        }

    }

    public static String genHash(String str)throws Exception     {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());

        byte[] dataBytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataBytes.length; i++) {
            sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}