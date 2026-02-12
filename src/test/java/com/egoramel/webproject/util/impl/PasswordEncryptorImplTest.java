package com.egoramel.webproject.util.impl;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorImplTest {

    private final PasswordEncryptorImpl encryptor = new PasswordEncryptorImpl();

    @Test
    void testEncryptReturnsNonNull() throws NoSuchAlgorithmException {
        String encrypted = encryptor.encrypt("password123");
        assertNotNull(encrypted);
    }

    @Test
    void testEncryptSamePasswordReturnsSameHash() throws NoSuchAlgorithmException {
        String encrypted1 = encryptor.encrypt("password123");
        String encrypted2 = encryptor.encrypt("password123");
        assertEquals(encrypted1, encrypted2);
    }

    @Test
    void testEncryptDifferentPasswordsReturnDifferentHashes() throws NoSuchAlgorithmException {
        String encrypted1 = encryptor.encrypt("password123");
        String encrypted2 = encryptor.encrypt("password456");
        assertNotEquals(encrypted1, encrypted2);
    }
}