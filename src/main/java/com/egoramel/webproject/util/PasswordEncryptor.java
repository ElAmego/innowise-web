package com.egoramel.webproject.util;

import java.security.NoSuchAlgorithmException;

public interface PasswordEncryptor {
    String encrypt(final String password) throws NoSuchAlgorithmException;
}
