package com.egoramel.webproject.util.impl;

import com.egoramel.webproject.util.PasswordEncryptor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class PasswordEncryptorImpl implements PasswordEncryptor {
    @Override
    public String encrypt(final String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(passwordBytes);

        byte[] hash = messageDigest.digest();
        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(hash);
    }
}